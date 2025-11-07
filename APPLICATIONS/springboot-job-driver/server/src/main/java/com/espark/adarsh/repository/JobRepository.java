package com.espark.adarsh.repository;

import com.espark.adarsh.bean.JobConfig;
import com.espark.adarsh.config.JobsConfigDetails;
import com.espark.adarsh.config.JobDetails;
import com.espark.adarsh.exception.InvalidJobAbortRequest;
import com.espark.adarsh.exception.InvalidJobRequestException;
import com.espark.adarsh.exception.JobExistException;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    static final Map<String, JobConfig> store = new HashMap<>();

    ScheduledExecutorService scheduledExecutorService;

    static final ConcurrentLinkedQueue<JobConfig> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

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
        LocalDateTime jobExitTime = localDateTime.plusMinutes(time);
        return jobExitTime;
    };

    final Supplier<String> generateJobId = () -> {
        return UUID.randomUUID().toString();
    };

    public final Function<String, JobConfig> jobStatusById = (jobId) -> {
        if (store.containsKey(jobId)) {
            return store.get(jobId);
        } else {
            throw new InvalidJobRequestException("Invalid Job Id " + jobId);
        }
    };

    public final Function<String, List<JobConfig>> jobStatusByName = (jobName) -> {
        List<JobConfig> jobConfigs = store.entrySet().stream().filter(jobEntry -> jobEntry.getValue().getJobName().equals(jobName)).map(e -> e.getValue()).toList();
        if (jobConfigs != null && !jobConfigs.isEmpty()) {
            return jobConfigs;
        } else {
            throw new InvalidJobRequestException("Invalid Job type for Status Request " + jobName);
        }
    };

    public final BiFunction<String, JobDetails, JobConfig> jobAbort = (jobName, jobDetails) -> {
        if (jobDetails.getAbort()) {
            throw new InvalidJobAbortRequest("Requested Job can't be configured for abort operation ");
        }
        Optional<JobConfig> jobOptional = store.entrySet().stream()
                .filter(jobEntry -> jobEntry.getValue().getJobName().equals(jobName))
                .map(entry -> entry.getValue())
                .findFirst();

        if (jobOptional.isPresent()) {
            String jobId = jobOptional.get().getJobId();
            if (store.containsKey(jobId)) {
                JobConfig jobConfig = store.get(jobId);
                jobConfig.setAbortRequest(true);
                jobConfig.setStatus("REQUEST_FOR_ABORT");
                jobConfig.setMessage("job is requested for abort ");
                store.put(jobId, jobConfig);
                return jobConfig;
            }
        }
        throw new InvalidJobAbortRequest("Invalid Job type for Abort Request " + jobName);
    };

    final Predicate<ConcurrentLinkedQueue<JobConfig>> isEmpty = (concurrentLinkedQueue) -> {
        synchronized (concurrentLinkedQueue) {
            return concurrentLinkedQueue.isEmpty();
        }
    };

    final Consumer<ConcurrentLinkedQueue<JobConfig>> jobsExecutors = (concurrentLinkedQueue) -> {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            CompletableFuture.runAsync(() -> {
                if (!isEmpty.test(concurrentLinkedQueue)) {
                    JobConfig jobConfig = concurrentLinkedQueue.poll();
                    log.info("Job Details Before Starting {}", jobConfig);
                    jobConfig.setStatus("STARTING");
                    jobConfig.setMessage("job is executing ");
                    store.put(jobConfig.getJobId(), jobConfig);
                } else if (!store.isEmpty()) {
                    log.info("executing jobs in job pools");
                    final Set<String> keySet = store.keySet();
                    keySet.stream().forEach(e -> {
                        JobConfig jobConfig = store.get(e);
                        log.info("Job Details while Executing {}", jobConfig);
                        final String status = jobConfig.getStatus();
                        switch (status) {
                            case "EXECUTING" -> {
                                if (LocalDateTime.now().isAfter(jobConfig.getExpectedCompletion())) {
                                    jobConfig.setStatus("COMPLETED");
                                    jobConfig.setMessage("job is completed ");
                                    store.put(jobConfig.getJobId(), jobConfig);
                                    log.info("Job Details After Completion {}", jobConfig);
                                }
                            }
                            case "ABORTED" -> {
                                jobConfig.setStatus("ABORTED");
                                jobConfig.setMessage("job is aborted ");
                                store.put(jobConfig.getJobId(), jobConfig);
                                log.info("Job Details After Aborted {}", jobConfig);
                            }
                            case "STARTING" -> {
                                jobConfig.setStatus("EXECUTING");
                                jobConfig.setMessage("job is executing ");
                                store.put(jobConfig.getJobId(), jobConfig);
                                log.info("Job Details before Executing job {} threadName {}", jobConfig, Thread.currentThread().getName());
                            }
                        }
                    });
                }

            });
        }, 0, 30, TimeUnit.SECONDS);
    };


    public final Predicate<JobConfig> checkJobExistInStore = (jobConfig -> {
        return store.containsKey(jobConfig.getJobName());
    });

    public final Predicate<JobConfig> checkJobExistInQueue = (jobConfig) -> {
        return concurrentLinkedQueue.stream().filter(e -> e.getJobName().equals(jobConfig.getJobName())).findFirst().isPresent();
    };

    public final BiFunction<JobConfig, JobDetails, JobConfig> startJob = (jobConfig, jobDetails) -> {
        if (checkJobExistInQueue.test(jobConfig) || checkJobExistInStore.test(jobConfig)) {
            throw new JobExistException("Job Already Exist " + jobConfig.getJobName());
        }
        jobConfig.setJobId(generateJobId.get());
        jobConfig.setStartedOn(LocalDateTime.now().toString());
        jobConfig.setExpectedCompletion(getJobExitTime.apply(jobDetails.getMaxRunTime()));
        jobConfig.setMessage("job is starting ");
        jobConfig.setStatus("IN-QUEUE");
        jobConfig.setAbortRequest(false);
        jobConfig.setStartedBy(System.getProperty("user.name"));
        store.put(jobConfig.getJobId(), jobConfig);
        concurrentLinkedQueue.add(jobConfig);
        return jobConfig;
    };


}
