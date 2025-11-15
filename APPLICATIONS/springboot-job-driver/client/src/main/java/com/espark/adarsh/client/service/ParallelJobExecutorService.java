package com.espark.adarsh.client.service;

import com.espark.adarsh.client.annotaton.ApiExecution;
import com.espark.adarsh.client.bean.JobConfig;
import com.espark.adarsh.client.config.ParallelJobConfig;
import com.espark.adarsh.client.exception.InvalidJobRequestException;
import com.espark.adarsh.client.service.executor.JobExecutorService;
import com.espark.adarsh.client.service.util.ServiceUtil;
import com.espark.adarsh.client.util.Constants;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Getter
@Slf4j
@ApiExecution(name = Constants.EXECUTE_PARALLEL)
public class ParallelJobExecutorService implements ApiExecutionService {

    private ServiceUtil serviceUtil;
    private ParallelJobConfig parallelJobConfig;
    private JobExecutorService jobExecutorService;

    public ParallelJobExecutorService(
            ServiceUtil serviceUtil,
            ParallelJobConfig parallelJobConfig,
            JobExecutorService jobExecutorService) {
        this.serviceUtil = serviceUtil;
        this.parallelJobConfig = parallelJobConfig;
        this.jobExecutorService = jobExecutorService;
    }

    @PostConstruct
    public void init() {
        log.info("Parallel Job Config Details : {}", parallelJobConfig.getExecutionConfig());
    }


    private final Function<String, JobConfig> processExecuteApiRequest = (type) -> {
        log.info("Executing Parallel Job of type: {}", type);
        final List<ParallelJobConfig.JobDetails> parallelJobs = this.parallelJobConfig.getExecutionConfig().get(type);
        List<CompletableFuture<JobConfig>> futureList = new LinkedList<>();
        JobConfig response = null;
        parallelJobs.parallelStream()
                .forEach(parallelJob -> {
                    ;
                    log.info("Submitting Parallel Job Task for jobName: {}", parallelJob.getName());
                    CompletableFuture<JobConfig> jobFuture = jobExecutorService.executeJobTask(parallelJob.getName());
                    futureList.add(jobFuture);
                });
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();
        List<JobConfig> jobConfigList = futureList.stream().map(CompletableFuture::join).toList();
        log.info("All Parallel Job Tasks completed for type: {} with responses: {}", type, jobConfigList);
        if (!jobConfigList.isEmpty()) {
            boolean allSuccess = jobConfigList.stream()
                    .allMatch(jobConfig -> serviceUtil.getExecuteExitStatus().test(jobConfig));
            if (allSuccess) {
                response = this.serviceUtil.getDefaultJobConfig().apply(type, Constants.COMPLETED);
            } else {
                response = this.serviceUtil.getDefaultJobConfig().apply(type, Constants.FAILED);
            }
        } else {
            log.error("No Job Configs found after executing Parallel Jobs for type: {}", type);
            response = this.serviceUtil.getDefaultJobConfig().apply(type, Constants.FAILED);
        }
        return response;
    };

    @Override
    public Integer executeApiRequest(String type) {
        if (!this.parallelJobConfig.getExecutionConfig().containsKey(type)) {
            log.error("No configuration found for parallel Job type: {}", type);
            throw new InvalidJobRequestException("No configuration found for parallel Job type: " + type);
        }
        JobConfig response = this.getProcessExecuteApiRequest().apply(type);
        log.info("Final Response of parallel Job type {}: {}", type, response);
        return (this.serviceUtil.getExecuteExitStatus().test(response) ? 0 : 1);
    }
}
