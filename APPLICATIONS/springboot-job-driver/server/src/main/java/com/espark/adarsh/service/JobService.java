package com.espark.adarsh.service;

import com.espark.adarsh.bean.JobDetail;
import com.espark.adarsh.config.JobsConfigDetails;
import com.espark.adarsh.config.JobConfig;
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

    public BiFunction<JobConfig, JobDetail, JobDetail> jobStart = (jobConfig, jobDetail) -> {
        log.info("start job config {} jobDetails {} ", jobDetail, jobConfig);
            return this.jobRepository.getStartJob().apply(jobDetail, jobConfig);
    };

    public Function<String, JobDetail> jobStatusById = (jobId) ->{
        log.info(" job status jobId {} ",jobId);
        return this.jobRepository.getJobStatusById().apply(jobId);
    };

    public Function<String, JobDetail>  jobStatusByName = (jobName) ->{
        log.info(" job status jobName {} ",jobName);
        return this.jobRepository.getJobStatusByName().apply(jobName);
    };

    public BiFunction<JobConfig, String, JobDetail> jobAbort = (jobConfig, jobName) ->{
        log.info("Abort job name {} jobDetails {} ",jobName, jobConfig);
        return this.jobRepository.getJobAbort().apply(jobName, jobConfig);
    };

}
