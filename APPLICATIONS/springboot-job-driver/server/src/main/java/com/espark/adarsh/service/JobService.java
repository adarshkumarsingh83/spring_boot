package com.espark.adarsh.service;

import com.espark.adarsh.bean.JobConfig;
import com.espark.adarsh.config.JobsConfigDetails;
import com.espark.adarsh.config.JobDetails;
import com.espark.adarsh.repository.JobRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public BiFunction<JobDetails,JobConfig, JobConfig> jobStart = (jobDetails,jobConfig) -> {
        log.info("start job config {} jobDetails {} ",jobConfig, jobDetails);
            return this.jobRepository.getStartJob().apply(jobConfig, jobDetails);
    };

    public Function<String,JobConfig> jobStatusById = (jobId) ->{
        log.info(" job status jobId {} ",jobId);
        return this.jobRepository.getJobStatusById().apply(jobId);
    };

    public Function<String, List<JobConfig>>  jobStatusByName = (jobName) ->{
        log.info(" job status jobName {} ",jobName);
        return this.jobRepository.getJobStatusByName().apply(jobName);
    };

    public BiFunction<JobDetails, String, List<JobConfig>> jobAbort = (jobDetails,jobName) ->{
        log.info("Abort job name {} jobDetails {} ",jobName, jobDetails);
        return this.jobRepository.getJobAbort().apply(jobName,jobDetails);
    };

}
