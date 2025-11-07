package com.espark.adarsh.repository;

import com.espark.adarsh.bean.JobConfig;
import com.espark.adarsh.config.JobConfigDetails;
import com.espark.adarsh.config.JobDetails;
import com.espark.adarsh.exception.InvalidJobRequestException;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

@Component
public class JobRepository {

    static final Logger log = LoggerFactory.getLogger(JobRepository.class);

    @Value("${job.config.job-max-thread}")
    final Integer maxSize = 5;

    static final Map<String, JobConfig> store = new HashMap<>();

    ScheduledExecutorService scheduledExecutorService;

    static final ConcurrentLinkedQueue<JobConfig> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    private JobConfigDetails jobConfigDetails;

    public JobRepository(JobConfigDetails jobConfigDetails) {
        this.jobConfigDetails = jobConfigDetails;
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
        List<JobConfig> jobConfigs = store.entrySet()
                .stream()
                .filter(jobEntry -> jobEntry.getValue().getJobName().equals(jobName))
                .map(e -> e.getValue())
                .toList();
        if (jobConfigs != null && !jobConfigs.isEmpty()) {
            return jobConfigs;
        } else {
            throw new InvalidJobRequestException("Invalid Job type for Status Request " + jobName);
        }
    };

    public final Function<String, List<JobConfig>> jobAbort = (jobName) -> {
        List<String> jobIdList = store.entrySet()
                .stream()
                .filter(jobEntry -> jobEntry.getValue().getJobName().equals(jobName))
                .map(e -> e.getValue().getJobId())
                .toList();
        if (jobIdList != null && !jobIdList.isEmpty()) {

            return jobIdList.stream()
                    .map(id -> {
                        if (store.containsKey(id)) {
                            JobConfig jobConfig = store.get(id);
                            jobConfig.setAbortRequest(true);
                            jobConfig.setStatus("REQUEST_FOR_ABORT");
                            jobConfig.setMessage("job is requested for abort ");
                            store.put(id, jobConfig);
                        }
                        return store.get(id);
                    }).toList();
        } else {
            throw new InvalidJobRequestException("Invalid Job type for Abort Request " + jobName);
        }
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
                }else if(!store.isEmpty()){
                    log.info("executing jobs in job pools");
                    final Set<String> keySet = store.keySet();
                     keySet.stream()
                            .forEach(e ->{
                                JobConfig jobConfig = store.get(e);
                                log.info("Job Details while Executing {}", jobConfig);
                                final String status =  jobConfig.getStatus();
                                switch (status){
                                    case "EXECUTING" -> {
                                        if(LocalDateTime.now().isAfter(jobConfig.getExpectedCompletion())){
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
                                        log.info("Job Details before Executing job {} threadName {}",
                                                jobConfig, Thread.currentThread().getName());
                                    }
                                }
                            });
                }

            });
        }, 0, 30, TimeUnit.SECONDS);
    };


    public final BiFunction<JobConfig, JobDetails, JobConfig> startJob = (jobConfig, jobDetails) -> {
        jobConfig.setJobId(generateJobId.get());
        jobConfig.setStartedOn(LocalDateTime.now().toString());
        jobConfig.setExpectedCompletion(getJobExitTime.apply(jobDetails.getMaxRunTime()));
        jobConfig.setMessage("job is starting ");
        jobConfig.setStatus("IN-QUEUE");
        jobConfig.setAbortRequest(false);
        jobConfig.setStartedBy(System.getProperty("user.name"));
        store.put(jobConfig.getJobId(),jobConfig);
        concurrentLinkedQueue.add(jobConfig);
        return jobConfig;
    };

}
