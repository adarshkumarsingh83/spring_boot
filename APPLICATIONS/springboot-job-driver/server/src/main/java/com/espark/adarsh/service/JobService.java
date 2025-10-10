package com.espark.adarsh.service;

import com.espark.adarsh.bean.JobConfig;
import com.espark.adarsh.config.JobConfigDetails;
import com.espark.adarsh.config.JobDetails;
import com.espark.adarsh.exception.InvalidJobRequestException;
import com.espark.adarsh.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class JobService {

    private JobRepository jobRepository;

    private JobConfigDetails jobConfigDetails;

    public JobService(JobRepository jobRepository,JobConfigDetails jobConfigDetails) {
        this.jobRepository = jobRepository;
        this.jobConfigDetails= jobConfigDetails;
    }

    public Function<JobConfig, JobConfig> jobStart = (jobConfig) -> {
        JobDetails jobDetails = jobConfigDetails.getJobTypes().containsKey(jobConfig.getJobName()) ?
                jobConfigDetails.getJobTypes().get(jobConfig.getJobName()) : null;
        if (jobDetails != null) {
            return this.jobRepository.startJob.apply(jobConfig, jobDetails);
        }
        throw new InvalidJobRequestException("Invalid Requested Job " + jobConfig.getJobName() + " ");
    };

    public Function<String,JobConfig> jobStatusById = (jobId) ->{
        return this.jobRepository.jobStatusById.apply(jobId);
    };

    public Function<String, List<JobConfig>>  jobStatusByName = (jobName) ->{
        return this.jobRepository.jobStatusByName.apply(jobName);
    };

    public Function<String, List<JobConfig>> jobAbort = (jobName) ->{
        return this.jobRepository.jobAbort.apply(jobName);
    };

}
