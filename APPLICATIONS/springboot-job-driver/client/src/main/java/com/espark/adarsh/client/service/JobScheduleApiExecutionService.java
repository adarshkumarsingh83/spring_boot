package com.espark.adarsh.client.service;

import com.espark.adarsh.client.annotaton.ApiExecution;
import com.espark.adarsh.client.bean.ApiResponse;
import com.espark.adarsh.client.bean.DefaultJobConfig;
import com.espark.adarsh.client.bean.JobConfig;
import com.espark.adarsh.client.component.transformer.ApiRequestTransformer;
import com.espark.adarsh.client.component.transformer.ApiResponseTransformer;
import com.espark.adarsh.client.config.MonitoringApiDetails;
import com.espark.adarsh.client.config.ScheduleApiConfigs;
import com.espark.adarsh.client.exception.ApiSchdulerException;
import com.espark.adarsh.client.exception.ResourceNotFoundException;
import com.espark.adarsh.client.service.integration.HttpGetApiIntegrationServiceImpl;
import com.espark.adarsh.client.service.integration.HttpPostApiIntegrationServiceImpl;
import com.espark.adarsh.client.util.Constants;
import com.espark.adarsh.client.util.transformer.TransformerProcessor;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;


import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
@Getter
@ApiExecution(name = Constants.CREATE)
public class JobScheduleApiExecutionService implements ApiExecutionService{

    @Value("${espark.job.scheduler.monitor.wait-time}")
    private int waitTime;


    private ScheduleApiConfigs scheduleApiConfigs;

    private TransformerProcessor transformerProcessor;

    private HttpPostApiIntegrationServiceImpl<DefaultJobConfig> httpPostApiIntegrationService;

    private HttpGetApiIntegrationServiceImpl<DefaultJobConfig> httpGetApiIntegrationService;


    @PostConstruct
    public void init() {
        log.info("JobScheduleApiExecutionService initialized with waitTime: {}", this.waitTime);
    }

    public JobScheduleApiExecutionService(ScheduleApiConfigs scheduleApiConfigs,
                                          TransformerProcessor transformerProcessor,
                                          HttpPostApiIntegrationServiceImpl<DefaultJobConfig> httpPostApiIntegrationService,
                                          HttpGetApiIntegrationServiceImpl<DefaultJobConfig> httpGetApiIntegrationService) {
        this.scheduleApiConfigs = scheduleApiConfigs;
        this.transformerProcessor = transformerProcessor;
        this.httpPostApiIntegrationService = httpPostApiIntegrationService;
        this.httpGetApiIntegrationService = httpGetApiIntegrationService;
    }

    private final Predicate<JobConfig> exitStatus = (jobConfig) -> {
        return switch (jobConfig.getJobStatus()) {
            case Constants.COMPLETED -> true;
            case Constants.FAILED, Constants.ABORT -> false;
            default -> throw new IllegalStateException("Unexpected value: " + jobConfig.getJobStatus());
        };
    };

    private final Predicate<String> proceedIteration = (status) -> {
        if (status != null && !status.isEmpty()) {
            return switch (status) {
                case Constants.START, Constants.EXECUTING, Constants.REQUEST_FOR_ABORT -> true;
                case Constants.COMPLETED, Constants.FAILED, Constants.ABORT -> false;
                default -> throw new IllegalStateException("Unexpected value: " + status);
            };
        }
        return false;
    };

    private final BiFunction<MonitoringApiDetails, DefaultJobConfig, DefaultJobConfig> monitoringJobProcess =
            (MonitoringApiDetails monitoringApiDetails, DefaultJobConfig jobConfig) -> {
                boolean run = false;
                ApiRequestTransformer<DefaultJobConfig> apiRequestTransformer = transformerProcessor.getRequestTransformer(monitoringApiDetails.getRequestTransformer());
                if (apiRequestTransformer != null) {
                    apiRequestTransformer.transformRequest(monitoringApiDetails, jobConfig);
                } else {
                    log.info("Monitoring Job Transformer not found for type: {}", jobConfig.getJobType());
                }
                int waitTime = monitoringApiDetails.getMonitorWaitTime() != null ? monitoringApiDetails.getMonitorWaitTime() : this.waitTime;
                do {
                    try {
                        String httpMethod = monitoringApiDetails.getHttpMethod().name();
                        monitoringApiDetails.setParameterizedTypeReference(new ParameterizedTypeReference<DefaultJobConfig>() {
                        });
                        ApiResponse<DefaultJobConfig> apiResponse = switch (httpMethod) {
                            case Constants.GET ->
                                    this.httpGetApiIntegrationService.getApiCallExecution(monitoringApiDetails, Constants.MONITOR_JOB_EXECUTION);
                            case Constants.POST ->
                                    this.httpPostApiIntegrationService.postApiCallExecution(monitoringApiDetails, Constants.MONITOR_JOB_EXECUTION);
                            default ->
                                    throw new IllegalArgumentException("Invalid HTTP Method requested : " + httpMethod);
                        };
                        Thread.sleep(waitTime);
                        run = apiResponse.getData() != null  ? proceedIteration.test(apiResponse.getData().getJobStatus()) : true;
                        jobConfig = apiResponse.getData();
                    } catch (InterruptedException e) {
                        log.error("Error while waiting for job status update", e);
                    }
                } while (run);

                ApiResponseTransformer<DefaultJobConfig> apiResponseTransformer = transformerProcessor.getResponseTransformer(monitoringApiDetails.getRequestTransformer());
                if (apiResponseTransformer != null) {
                    apiResponseTransformer.transformResponse(monitoringApiDetails, jobConfig);
                } else {
                    log.info("Monitoring Job Response Transformer not found for type: {}", jobConfig.getJobType());
                }

                return jobConfig;
            };


    private final Function<String, DefaultJobConfig> processJobRequest = (type) -> {
        log.info("Processing job request for type: {}", type);
        ScheduleApiConfigs.ApiDetails apiDetails = scheduleApiConfigs.getApiConfigs().get(type);
        if (apiDetails != null) {
            ApiRequestTransformer<DefaultJobConfig> apiRequestTransformer = transformerProcessor.getRequestTransformer(apiDetails.getRequestTransformer());
            if (apiRequestTransformer != null) {
                apiRequestTransformer.transformRequest(apiDetails, null);
            } else {
                log.info("No Request Transformer found for type: {}", type);
            }
            apiDetails.setParameterizedTypeReference(new ParameterizedTypeReference<DefaultJobConfig>() {});
            String httpMethod = apiDetails.getHttpMethod().name();
            ApiResponse<DefaultJobConfig> apiResponse = switch (httpMethod) {
                case Constants.GET -> this.httpGetApiIntegrationService.getApiCallExecution(apiDetails, type);
                case Constants.POST -> this.httpPostApiIntegrationService.postApiCallExecution(apiDetails, type);
                default -> throw new IllegalArgumentException("Invalid HTTP Method requested : " + httpMethod);
            };

            if (apiDetails.getMonitorJobExecution() != null) {
                if (apiResponse.getData() != null) {
                    apiResponse.getData().setJobType(type);
                    if (proceedIteration.test(apiResponse.getData().getJobStatus())) {
                        apiResponse.setData(monitoringJobProcess.apply(apiDetails.getMonitorJobExecution(), apiResponse.getData()));
                    }
                    return apiResponse.getData();
                }
                throw new ApiSchdulerException("Response From Server is null for : " + type);
            }

            ApiResponseTransformer apiResponseTransformer = transformerProcessor.getResponseTransformer(apiDetails.getResponseTransformer());
            if (apiResponseTransformer != null) {
                apiResponseTransformer.transformResponse(apiDetails, apiResponse.getData());
            } else {
                log.info("No Response Transformer found for type: {}", type);
            }
            return apiResponse.getData();
        }
        throw new ApiSchdulerException("Api Details not found for type: " + type);
    };

    final private Function<String, JobConfig> requestRouter = (type) -> {
        log.info("Request Router invoked for type: {}", type);

        if (scheduleApiConfigs.getApiConfigs().containsKey(type)) {
            log.info("Processing request for type: {}", type);
            return processJobRequest.apply(type);
        }
        throw new ResourceNotFoundException("Invalid Job Type Requested " + type);
    };

    private final  Function<String, Integer> processExecuteApiRequest = (type) -> {
        log.info("Processing API request for type: {}", type);
        JobConfig response = requestRouter.apply(type);
        log.info("Response received: for type {} status {} response {}", type, response.getJobStatus(), response);
        return (exitStatus.test(response) ? 0 : 1);
    };

    public Integer executeApiRequest(String type){
         return this.getProcessExecuteApiRequest().apply(type);
    }
}
