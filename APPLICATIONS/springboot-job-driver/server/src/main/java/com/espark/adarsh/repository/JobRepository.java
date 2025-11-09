package com.espark.adarsh.repository;

import com.espark.adarsh.bean.JobDetail;
import com.espark.adarsh.config.JobsConfigDetails;
import com.espark.adarsh.config.OnRequestJobConfig;
import com.espark.adarsh.exception.InvalidJobAbortRequestException;
import com.espark.adarsh.exception.InvalidJobRequestException;
import com.espark.adarsh.exception.JobExistException;
import com.espark.adarsh.service.JobExecutorService;
import com.espark.adarsh.store.DataStore;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
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

    private DataStore dataStore;

    private JobExecutorService jobExecutorService;

    private final JobsConfigDetails jobsConfigDetails;

    public JobRepository(JobsConfigDetails jobsConfigDetails, DataStore dataStore,
                         JobExecutorService jobExecutorService) {
        this.jobsConfigDetails = jobsConfigDetails;
        this.dataStore = dataStore;
        this.jobExecutorService = jobExecutorService;
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
        if (dataStore.getExecutingStoreSupplier().get().containsKey(jobId)) {
            return dataStore.getExecutingStoreSupplier().get().get(jobId);
        } else if (dataStore.getCompletedStoreSupplier().get().containsKey(jobId)) {
            return dataStore.getCompletedStoreSupplier().get().get(jobId);
        } else if (dataStore.getFailedStoreSupplier().get().containsKey(jobId)) {
            return dataStore.getFailedStoreSupplier().get().get(jobId);
        } else {
            throw new InvalidJobRequestException("Invalid Job Id " + jobId);
        }
    };


    public final Function<String, JobDetail> jobStatusByName = (jobName) -> {
        Optional<JobDetail> jobOptional = dataStore.getJobExistInExecutingStore().apply(jobName);
        if (jobOptional.isPresent()) {
            return jobOptional.get();
        }

        jobOptional = dataStore.getJobExistInCompleteStore().apply(jobName);
        if (jobOptional.isPresent()) {
            return jobOptional.get();
        }

        jobOptional = dataStore.getJobExistInFailedStore().apply(jobName);
        if (jobOptional.isPresent()) {
            return jobOptional.get();
        }

        throw new InvalidJobRequestException("Invalid Job type for Status Request " + jobName);
    };

    public final BiFunction<String, OnRequestJobConfig, JobDetail> jobAbort = (jobName, onRequestJobConfig) -> {
        if (onRequestJobConfig.getAbort()) {
            throw new InvalidJobAbortRequestException("Requested Job can't be configured for abort operation ");
        }

        Optional<JobDetail> jobOptional = dataStore.getJobExistInExecutingStore().apply(jobName);
        if (jobOptional.isPresent()) {
            String jobId = jobOptional.get().getJobId();
            if (dataStore.getExecutingStoreSupplier().get().containsKey(jobId)) {
                JobDetail jobDetail = dataStore.getExecutingStoreSupplier().get().get(jobId);
                jobDetail.setAbortRequest(true);
                jobDetail.setStatus("REQUEST_FOR_ABORT");
                jobDetail.setMessage("job is requested for abort ");
                dataStore.getExecutingStoreSupplier().get().put(jobId, jobDetail);
                return jobDetail;
            }
        }

        jobOptional = dataStore.getJobExistInCompleteStore().apply(jobName);
        if (jobOptional.isPresent()) {
            throw new InvalidJobAbortRequestException("Job Already Completed its execution " + jobOptional.get().getJobId());
        }

        jobOptional = dataStore.getJobExistInFailedStore().apply(jobName);
        if (jobOptional.isPresent()) {
            throw new InvalidJobAbortRequestException("Job Already Failed its execution " + jobOptional.get().getJobId());
        }

        throw new InvalidJobAbortRequestException("Invalid Job type for Abort Request Job Not Found " + jobName);
    };


    public final BiFunction<JobDetail, OnRequestJobConfig, JobDetail> startJob = (jobDetail, onRequestJobConfig) -> {

        if (this.jobExecutorService.getCheckJobExistInQueue().test(jobDetail) || dataStore.getCheckJobExistInStore().test(jobDetail)) {
            throw new JobExistException("Job Already Exist in Executing State " + jobDetail.getJobName());
        }

        if (dataStore.getDependentJobExist().test(onRequestJobConfig.getConflict())) {
            if (onRequestJobConfig.getAction().equals("ABORT")) {
                throw new InvalidJobRequestException(onRequestJobConfig.getMessage());
            }

            if (onRequestJobConfig.getAction().equals("WAITING")) {
                boolean result = false;
                do {
                    try {
                        TimeUnit.MILLISECONDS.sleep(Duration.ofSeconds(onRequestJobConfig.getWaitTime()).toMillis());
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                    result = dataStore.getDependentJobExist().test(onRequestJobConfig.getConflict());
                } while (result);
            }
        }

        jobDetail.setJobId(generateJobId.get());
        jobDetail.setStartedOn(LocalDateTime.now());
        jobDetail.setExpectedCompletion(getJobExitTime.apply(onRequestJobConfig.getMaxRunTime()));
        jobDetail.setMessage("job is starting ");
        jobDetail.setStatus("IN-QUEUE");
        jobDetail.setAbortRequest(false);
        jobDetail.setStartedBy(System.getProperty("user.name"));
        dataStore.getExecutingStoreSupplier().get().put(jobDetail.getJobId(), jobDetail);
        jobExecutorService.getConcurrentLinkedQueueSupplier().get().add(jobDetail);
        return jobDetail;
    };


}
