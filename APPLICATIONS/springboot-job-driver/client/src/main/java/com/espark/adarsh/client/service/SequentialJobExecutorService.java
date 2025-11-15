package com.espark.adarsh.client.service;

import com.espark.adarsh.client.annotaton.ApiExecution;
import com.espark.adarsh.client.bean.JobConfig;
import com.espark.adarsh.client.config.SequentialJobConfig;
import com.espark.adarsh.client.exception.InvalidJobRequestException;
import com.espark.adarsh.client.service.util.ServiceUtil;
import com.espark.adarsh.client.util.Constants;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;


@Getter
@Slf4j
@ApiExecution(name = Constants.EXECUTE_SEQUENTIAL)
public class SequentialJobExecutorService implements ApiExecutionService {

    private ServiceUtil serviceUtil;
    private SequentialJobConfig sequentialJobConfig;
    private JobScheduleApiExecutionService jobScheduleApiExecutionService;

    public SequentialJobExecutorService(
            ServiceUtil serviceUtil,
            SequentialJobConfig sequentialJobConfig,
            JobScheduleApiExecutionService jobScheduleApiExecutionService) {
        this.serviceUtil = serviceUtil;
        this.sequentialJobConfig = sequentialJobConfig;
        this.jobScheduleApiExecutionService = jobScheduleApiExecutionService;
    }

    @PostConstruct
    public void init() {
        log.info("Sequential Job Config Details : {}", sequentialJobConfig.getExecutionConfig());
    }


    private final Function<String, JobConfig> processExecuteApiRequest = (type) -> {
        log.info("Executing Sequential Job of type: {}", type);
        List<String> executionList = this.sequentialJobConfig.getExecutionConfig().get(type);
        boolean executeNext = false;
        JobConfig response = null;
        int index = 0;
        do {
            if (this.jobScheduleApiExecutionService.getScheduleJobConfigs().getApiConfigs().containsKey(executionList.get(index))) {
                log.info("Executing step {} of Sequential Job type: {}", executionList.get(index), type);
                response = this.jobScheduleApiExecutionService.getProcessJobRequest().apply(executionList.get(index));
                log.info("Response of step {}: {}", executionList.get(index), response);
                executeNext = this.serviceUtil.getExecuteExitStatus().test(response);
                if (!executeNext) {
                    log.info("Halting execution of Sequential Job type: {} at step: {} due to exit status: {}", type, executionList.get(index), response.getJobStatus());
                    if ((index) < executionList.size() - 1) {
                        IntStream.range(index + 1, executionList.size()).forEach(i -> {
                            log.info("Skipping next index {} step {} of Sequential Job type: {}", i, executionList.get(i), type);
                        });
                    }
                } else {
                    log.info("Proceeding to next step of Sequential Job type: {} after successful execution of step: {}", type, executionList.get(index));
                    if ((index) < executionList.size() - 1) {
                        IntStream.range(index + 1, executionList.size()).forEach(i -> {
                            log.info("Preparing next index {} step {} of Sequential Job type: {}", i, executionList.get(i), type);
                        });
                    }
                }
            } else {
                log.error("No API configuration found for step: {}", executionList.get(index));
                throw new InvalidJobRequestException("No API configuration found for step: " + executionList.get(index));
            }
            index++;
        } while (executeNext && index < executionList.size());

        return response;
    };

    @Override
    public Integer executeApiRequest(String type) {
        if (!this.sequentialJobConfig.getExecutionConfig().containsKey(type)) {
            log.error("No configuration found for Sequential Job type: {}", type);
            throw new InvalidJobRequestException("No configuration found for Sequential Job type: " + type);
        }
        JobConfig response = this.getProcessExecuteApiRequest().apply(type);
        log.info("Final Response of Sequential Job type {}: {}", type, response);
        return (this.serviceUtil.getExecuteExitStatus().test(response) ? 0 : 1);
    }

}
