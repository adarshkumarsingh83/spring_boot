package com.espark.adarsh.service;

import com.espark.adarsh.bean.JobDetail;
import com.espark.adarsh.config.JobsConfigDetails;
import com.espark.adarsh.config.OnRequestJobConfig;
import com.espark.adarsh.repository.JobRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
@Getter
@Service
public class JobService {

    private JobRepository jobRepository;

    private final JobsConfigDetails jobsConfigDetails;

    public JobService(JobRepository jobRepository, JobsConfigDetails jobsConfigDetails) {
        this.jobRepository = jobRepository;
        this.jobsConfigDetails = jobsConfigDetails;
    }

    public BiFunction<OnRequestJobConfig, JobDetail, JobDetail> jobStart = (onRequestJobConfig, jobDetail) -> {
        log.info("start job config {} jobDetails {} ", jobDetail, onRequestJobConfig);
            return this.jobRepository.getStartJob().apply(jobDetail, onRequestJobConfig);
    };

    public Function<String, JobDetail> jobStatusById = (jobId) ->{
        log.info(" job status jobId {} ",jobId);
        return this.jobRepository.getJobStatusById().apply(jobId);
    };

    public Function<String, JobDetail>  jobStatusByName = (jobName) ->{
        log.info(" job status jobName {} ",jobName);
        return this.jobRepository.getJobStatusByName().apply(jobName);
    };

    public BiFunction<OnRequestJobConfig, String, JobDetail> jobAbort = (onRequestJobConfig, jobName) ->{
        log.info("Abort job name {} jobDetails {} ",jobName, onRequestJobConfig);
        return this.jobRepository.getJobAbort().apply(jobName, onRequestJobConfig);
    };

}
