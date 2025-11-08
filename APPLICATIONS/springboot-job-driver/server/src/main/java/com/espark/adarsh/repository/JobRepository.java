package com.espark.adarsh.repository;

import com.espark.adarsh.bean.JobDetail;
import com.espark.adarsh.config.JobsConfigDetails;
import com.espark.adarsh.config.JobConfig;
import com.espark.adarsh.exception.InvalidJobAbortRequestException;
import com.espark.adarsh.exception.InvalidJobRequestException;
import com.espark.adarsh.exception.JobExistException;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

@Getter
@Slf4j
@Component
public class JobRepository {

    @Value("${job.config.job-max-thread}")
    final Integer maxSize = 5;

    static final Map<String, JobDetail> executingStore = new HashMap<>();
    static final Map<String, JobDetail> completedStore = new HashMap<>();
    static final Map<String, JobDetail> failedStore = new HashMap<>();

    ScheduledExecutorService scheduledExecutorService;

    static final ConcurrentLinkedQueue<JobDetail> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    private JobsConfigDetails jobsConfigDetails;

    public JobRepository(JobsConfigDetails jobsConfigDetails) {
        this.jobsConfigDetails = jobsConfigDetails;
    }

    @PostConstruct
    public void init() {
        scheduledExecutorService = Executors.newScheduledThreadPool(maxSize);
        jobsExecutors.accept(concurrentLinkedQueue);
    }

    final Function<String, LocalDateTime> getJobExitTime = (jobTime) -> {
        Long time = Long.parseLong(jobTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.plusMinutes(time);
    };

    final Supplier<String> generateJobId = () -> {
        return UUID.randomUUID().toString();
    };

    public final Function<String, JobDetail> jobStatusById = (jobId) -> {
        if (executingStore.containsKey(jobId)) {
            return executingStore.get(jobId);
        } else if (completedStore.containsKey(jobId)) {
            return completedStore.get(jobId);
        } else if (failedStore.containsKey(jobId)) {
            return failedStore.get(jobId);
        } else {
            throw new InvalidJobRequestException("Invalid Job Id " + jobId);
        }
    };


    public final Function<String, Optional<JobDetail>> jobExistInExecutingStore = (jobName) -> {
        return executingStore.entrySet()
                .stream()
                .filter(jobEntry -> jobEntry.getValue().getJobName().equals(jobName))
                .map(Map.Entry::getValue)
                .findFirst();
    };

    public final Function<String, Optional<JobDetail>> jobExistInCompleteStore = (jobName) -> {
        return completedStore.entrySet()
                .stream()
                .filter(jobEntry -> jobEntry.getValue().getJobName().equals(jobName))
                .map(Map.Entry::getValue)
                .findFirst();
    };

    public final Function<String, Optional<JobDetail>> jobExistInFailedStore = (jobName) -> {
        return failedStore.entrySet()
                .stream()
                .filter(jobEntry -> jobEntry.getValue().getJobName().equals(jobName))
                .map(Map.Entry::getValue)
                .findFirst();
    };


    public final Function<String, JobDetail> jobStatusByName = (jobName) -> {
        Optional<JobDetail> jobOptional = jobExistInExecutingStore.apply(jobName);
        if (jobOptional.isPresent()) {
            return jobOptional.get();
        }

        jobOptional = jobExistInCompleteStore.apply(jobName);
        if (jobOptional.isPresent()) {
            return jobOptional.get();
        }

        jobOptional = jobExistInFailedStore.apply(jobName);
        if (jobOptional.isPresent()) {
            return jobOptional.get();
        }

        throw new InvalidJobRequestException("Invalid Job type for Status Request " + jobName);
    };

    public final BiFunction<String, JobConfig, JobDetail> jobAbort = (jobName, jobConfig) -> {
        if (jobConfig.getAbort()) {
            throw new InvalidJobAbortRequestException("Requested Job can't be configured for abort operation ");
        }

        Optional<JobDetail> jobOptional = jobExistInExecutingStore.apply(jobName);
        if (jobOptional.isPresent()) {
            String jobId = jobOptional.get().getJobId();
            if (executingStore.containsKey(jobId)) {
                JobDetail jobDetail = executingStore.get(jobId);
                jobDetail.setAbortRequest(true);
                jobDetail.setStatus("REQUEST_FOR_ABORT");
                jobDetail.setMessage("job is requested for abort ");
                executingStore.put(jobId, jobDetail);
                return jobDetail;
            }
        }

        jobOptional = jobExistInCompleteStore.apply(jobName);
        if (jobOptional.isPresent()) {
            throw new InvalidJobAbortRequestException("Job Already Completed its execution " + jobOptional.get().getJobId());
        }

        jobOptional = jobExistInFailedStore.apply(jobName);
        if (jobOptional.isPresent()) {
            throw new InvalidJobAbortRequestException("Job Already Failed its execution " + jobOptional.get().getJobId());
        }

        throw new InvalidJobAbortRequestException("Invalid Job type for Abort Request Job Not Found " + jobName);
    };

    final Predicate<ConcurrentLinkedQueue<JobDetail>> isEmpty = (concurrentLinkedQueue) -> {
        synchronized (concurrentLinkedQueue) {
            return concurrentLinkedQueue.isEmpty();
        }
    };

    final Consumer<ConcurrentLinkedQueue<JobDetail>> jobsExecutors = (concurrentLinkedQueue) -> {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            CompletableFuture.runAsync(() -> {
                if (!isEmpty.test(concurrentLinkedQueue)) {
                    JobDetail jobDetail = concurrentLinkedQueue.poll();
                    log.info("Job Details Before Starting {}", jobDetail);
                    jobDetail.setStatus("STARTING");
                    jobDetail.setMessage("job is executing ");
                    executingStore.put(jobDetail.getJobId(), jobDetail);
                } else if (!executingStore.isEmpty()) {
                    log.info("executing jobs in job pools");
                    final Set<String> keySet = executingStore.keySet();
                    keySet.stream().forEach(e -> {
                        JobDetail jobDetail = executingStore.get(e);
                        log.info("Job Details while Executing {}", jobDetail);
                        final String status = jobDetail.getStatus();
                        switch (status) {
                            case "EXECUTING" -> {
                                if (LocalDateTime.now().isAfter(jobDetail.getExpectedCompletion())) {
                                    jobDetail.setStatus("COMPLETED");
                                    jobDetail.setMessage("job is completed ");
                                    completedStore.put(jobDetail.getJobId(), jobDetail);
                                    executingStore.remove(jobDetail.getJobId());
                                    log.info("Job Details After Completion {}", jobDetail);
                                }
                            }
                            case "ABORTED" -> {
                                jobDetail.setStatus("ABORTED");
                                jobDetail.setMessage("job is aborted ");
                                failedStore.put(jobDetail.getJobId(), jobDetail);
                                executingStore.remove(jobDetail.getJobId());
                                log.info("Job Details After Aborted {}", jobDetail);
                            }
                            case "STARTING" -> {
                                jobDetail.setStatus("EXECUTING");
                                jobDetail.setMessage("job is executing ");
                                executingStore.put(jobDetail.getJobId(), jobDetail);
                                log.info("Job Details before Executing job {} threadName {}", jobDetail, Thread.currentThread().getName());
                            }
                        }
                    });
                }

            });
        }, 0, 30, TimeUnit.SECONDS);
    };


    public final Predicate<JobDetail> checkJobExistInStore = (jobDetail -> {
        return executingStore.containsKey(jobDetail.getJobName());
    });

    public final Predicate<JobDetail> checkJobExistInQueue = (jobDetail) -> {
        return concurrentLinkedQueue.stream().anyMatch(e -> e.getJobName().equals(jobDetail.getJobName()));
    };

    public final Predicate<List<String>> dependentJobExist = (jobs) -> {
        return jobs.parallelStream().anyMatch(e -> {
            return jobExistInExecutingStore.apply(e).isPresent();
        });
    };

    public final BiFunction<JobDetail, JobConfig, JobDetail> startJob = (jobDetail, jobConfig) -> {

        if (checkJobExistInQueue.test(jobDetail) || checkJobExistInStore.test(jobDetail)) {
            throw new JobExistException("Job Already Exist in Executing State " + jobDetail.getJobName());
        }

        if (dependentJobExist.test(jobConfig.getConflict())) {
            if(jobConfig.getAction().equals("ABORT")) {
                throw new InvalidJobRequestException(jobConfig.getMessage());
            }

            if (jobConfig.getAction().equals("WAITING")) {
                boolean result = false;
                do {
                    try {
                        TimeUnit.MILLISECONDS.sleep(Duration.ofSeconds(jobConfig.getWaitTime()).toMillis());
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                    result = dependentJobExist.test(jobConfig.getConflict());
                } while(result);
            }
        }

        jobDetail.setJobId(generateJobId.get());
        jobDetail.setStartedOn(LocalDateTime.now().toString());
        jobDetail.setExpectedCompletion(getJobExitTime.apply(jobConfig.getMaxRunTime()));
        jobDetail.setMessage("job is starting ");
        jobDetail.setStatus("IN-QUEUE");
        jobDetail.setAbortRequest(false);
        jobDetail.setStartedBy(System.getProperty("user.name"));
        executingStore.put(jobDetail.getJobId(), jobDetail);
        concurrentLinkedQueue.add(jobDetail);
        return jobDetail;
    };


}
