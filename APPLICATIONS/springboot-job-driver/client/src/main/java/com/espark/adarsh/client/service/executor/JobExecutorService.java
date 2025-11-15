package com.espark.adarsh.client.service.executor;

import com.espark.adarsh.client.bean.JobConfig;
import com.espark.adarsh.client.service.JobScheduleApiExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class JobExecutorService {

    private  final JobScheduleApiExecutionService jobScheduleApiExecutionService;

    public JobExecutorService(JobScheduleApiExecutionService jobScheduleApiExecutionService) {
        this.jobScheduleApiExecutionService = jobScheduleApiExecutionService;
    }

    @Async("asyncJobExecutor")
    public CompletableFuture<JobConfig> executeJobTask(String jobName){
        log.info("Executing Parallel Job Task for jobName: {}",jobName);
        JobConfig response = this.jobScheduleApiExecutionService.getProcessJobRequest().apply(jobName);
        log.info("Completed Parallel Job Task for jobName: {} with response: {}",jobName,response);
        return CompletableFuture.completedFuture(response);
    }

}
