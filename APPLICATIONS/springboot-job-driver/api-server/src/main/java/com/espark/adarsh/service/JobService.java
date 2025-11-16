package com.espark.adarsh.service;

import com.espark.adarsh.config.OnRequestJobConfig;
import com.espark.adarsh.entities.JobDetail;
import com.espark.adarsh.entities.JobStatus;
import com.espark.adarsh.exception.InvalidJobAbortRequestException;
import com.espark.adarsh.exception.JobExistException;
import com.espark.adarsh.repository.JobRepository;
import com.espark.adarsh.service.util.JobUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
@Slf4j
@Service
public class JobService {

    private JobUtil jobUtil;
    private JobRepository jobRepository;

    public JobService(JobUtil jobUtil, JobRepository jobRepository) {
        this.jobUtil= jobUtil;
        this.jobRepository = jobRepository;
    }

    public BiFunction<OnRequestJobConfig, JobDetail, JobDetail> jobStart = (onRequestJobConfig, jobDetail) -> {
        log.info("start job config {} jobDetails {} ", jobDetail, onRequestJobConfig);

        if (!this.jobRepository.findByJobNameInAndJobStatusIn(onRequestJobConfig.getConflict(),
                        List.of(JobStatus.IN_QUEUE.getStatus(), JobStatus.EXECUTING.getStatus()))
                .isEmpty()) {
            throw new JobExistException("Job Already Exist in Executing State " + jobDetail.getJobName());
        }
        jobDetail.setJobStatus(JobStatus.IN_QUEUE);
        jobDetail.setJobMessage("Job is in Queue");
        jobDetail.setExpectedCompletion(this.jobUtil.getGetJobExitTime().apply(onRequestJobConfig.getMaxRunTime()));
        jobDetail.setJobMessage("job is starting ");
        jobDetail.setAbortRequest(false);
        jobDetail.setStartedBy(System.getProperty("user.name"));
        jobDetail = this.jobRepository.save(jobDetail);
        log.info("Job saved successfully {} ", jobDetail);
        return jobDetail;
    };

    public Function<Long, JobDetail> jobStatusById = (jobId) -> {
        log.info(" job status jobId {} ", jobId);
        return this.jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Invalid Job Id " + jobId));
    };

    public Function<String, JobDetail> jobStatusByName = (jobName) -> {
        log.info(" job status jobName {} ", jobName);
        return this.jobRepository.findByJobName(jobName);
    };

    public BiFunction<OnRequestJobConfig, String, JobDetail> jobAbort = (onRequestJobConfig, jobName) -> {
        log.info("Abort job name {} jobDetails {} ", jobName, onRequestJobConfig);
        if (onRequestJobConfig.getAbort()) {
            throw new InvalidJobAbortRequestException("Requested Job can't be configured for abort operation ");
        }
        return this.jobRepository.findByJobNameInAndJobStatusIn(List.of(jobName), List.of(JobStatus.IN_QUEUE.getStatus()
                        , JobStatus.EXECUTING.getStatus(), JobStatus.WAITING.getStatus()))
                .stream().findFirst()
                .map(jobDetail -> {
                    jobDetail.setJobStatus(JobStatus.REQUEST_FOR_ABORT);
                    jobDetail.setJobMessage("Job Aborted Request Successfully");
                    return this.jobRepository.save(jobDetail);
                }).orElseThrow(() -> new RuntimeException("No Job Found to Abort for the name " + jobName));
    };
}
