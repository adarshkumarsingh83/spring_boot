package com.espark.adarsh.listner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
public class CommonBatchJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info(" Starting job = {} ", jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        log.info(" Job = {} finished  with status = {} ", jobExecution.getJobInstance().getJobName(),
                jobExecution.getStatus());
    }
}

