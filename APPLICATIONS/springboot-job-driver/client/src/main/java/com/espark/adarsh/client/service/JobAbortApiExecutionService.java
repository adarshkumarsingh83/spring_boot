package com.espark.adarsh.client.service;

import com.espark.adarsh.client.annotaton.ApiExecution;
import com.espark.adarsh.client.bean.ApiResponse;
import com.espark.adarsh.client.bean.DefaultJobConfig;
import com.espark.adarsh.client.bean.JobConfig;
import com.espark.adarsh.client.component.transformer.ApiRequestTransformer;
import com.espark.adarsh.client.component.transformer.ApiResponseTransformer;
import com.espark.adarsh.client.config.AbortApiConfigs;
import com.espark.adarsh.client.config.MonitoringApiDetails;
import com.espark.adarsh.client.config.ScheduleApiConfigs;
import com.espark.adarsh.client.exception.ApiSchdulerException;
import com.espark.adarsh.client.exception.ResourceNotFoundException;
import com.espark.adarsh.client.service.integration.HttpGetApiIntegrationServiceImpl;
import com.espark.adarsh.client.service.integration.HttpPostApiIntegrationServiceImpl;
import com.espark.adarsh.client.service.integration.HttpPutApiIntegrationServiceImpl;
import com.espark.adarsh.client.util.Constants;
import com.espark.adarsh.client.util.transformer.TransformerProcessor;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
@Getter
@ApiExecution(name = Constants.ABORT)
public class JobAbortApiExecutionService implements ApiExecutionService {

    @Value("${espark.job.scheduler.monitor.wait-time}")
    private int waitTime;


    private AbortApiConfigs abortApiConfigs;

    private HttpPutApiIntegrationServiceImpl<List<DefaultJobConfig>> httpPutApiIntegrationService;

    private HttpGetApiIntegrationServiceImpl<DefaultJobConfig> httpGetApiIntegrationService;

    private HttpPostApiIntegrationServiceImpl<DefaultJobConfig> httpPostApiIntegrationService;


    @PostConstruct
    public void init() {
        log.info("JobScheduleApiExecutionService initialized with waitTime: {}", this.waitTime);
    }

    public JobAbortApiExecutionService(AbortApiConfigs abortApiConfigs,
                                       HttpPutApiIntegrationServiceImpl<List<DefaultJobConfig>> httpPutApiIntegrationService,
                                       HttpGetApiIntegrationServiceImpl<DefaultJobConfig> httpGetApiIntegrationService,
                                       HttpPostApiIntegrationServiceImpl<DefaultJobConfig> httpPostApiIntegrationService) {
        this.abortApiConfigs = abortApiConfigs;
        this.httpPutApiIntegrationService = httpPutApiIntegrationService;
        this.httpGetApiIntegrationService = httpGetApiIntegrationService;
        this.httpPostApiIntegrationService = httpPostApiIntegrationService;
    }

    private final Predicate<JobConfig> exitStatus = (jobConfig) -> {
        return switch (jobConfig.getJobStatus()) {
            case Constants.COMPLETED ,Constants.ABORT-> true;
            case Constants.FAILED -> false;
            default -> throw new IllegalStateException("Unexpected value: " + jobConfig.getJobStatus());
        };
    };

    private final Predicate<String> proceedIteration = (status) -> {
        if (status != null && !status.isEmpty()) {
            return switch (status) {
                case Constants.STARTING, Constants.WAITING, Constants.IN_QUEUE, Constants.REQUEST_FOR_ABORT, Constants.EXECUTING -> true;
                case Constants.COMPLETED, Constants.FAILED, Constants.ABORT -> false;
                default -> throw new IllegalStateException("Unexpected value: " + status);
            };
        }
        return false;
    };

    private final BiFunction<MonitoringApiDetails, DefaultJobConfig, DefaultJobConfig> monitoringJobProcess =
            (MonitoringApiDetails monitoringApiDetails, DefaultJobConfig jobConfig) -> {
                boolean run = false;
                ApiRequestTransformer<DefaultJobConfig> apiRequestTransformer
                        = TransformerProcessor.getRequestTransformer(monitoringApiDetails.getRequestTransformer());
                if (apiRequestTransformer != null) {
                    apiRequestTransformer.transformRequest(monitoringApiDetails, jobConfig);
                } else {
                    log.info("Monitoring Job Transformer not found for type: {}", jobConfig.getJobType());
                }
                int waitTime = monitoringApiDetails.getMonitorWaitTime() != null ? monitoringApiDetails.getMonitorWaitTime() : this.waitTime;
                do {
                    try {
                        String httpMethod = monitoringApiDetails.getHttpMethod().name();
                        monitoringApiDetails.setParameterizedTypeReference(new ParameterizedTypeReference<ApiResponse<DefaultJobConfig>>() {
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

                ApiResponseTransformer<DefaultJobConfig> apiResponseTransformer
                        = TransformerProcessor.getResponseTransformer(monitoringApiDetails.getRequestTransformer());
                if (apiResponseTransformer != null) {
                    apiResponseTransformer.transformResponse(monitoringApiDetails, jobConfig);
                } else {
                    log.info("Monitoring Job Response Transformer not found for type: {}", jobConfig.getJobType());
                }

                return jobConfig;
            };


    private final Function<String, List<DefaultJobConfig>> processJobRequest = (type) -> {
        log.info("Processing job request for type: {}", type);
        AbortApiConfigs.ApiDetails apiDetails = abortApiConfigs.getApiConfigs().get(type);
        if (apiDetails != null) {
            ApiRequestTransformer<DefaultJobConfig> apiRequestTransformer
                    = TransformerProcessor.getRequestTransformer(apiDetails.getRequestTransformer());
            if (apiRequestTransformer != null) {
                apiRequestTransformer.transformRequest(apiDetails, null);
            } else {
                log.info("No Request Transformer found for type: {}", type);
            }
            apiDetails.setParameterizedTypeReference(new ParameterizedTypeReference<ApiResponse<DefaultJobConfig>>() {
            });
            String httpMethod = apiDetails.getHttpMethod().name();
            ApiResponse<List<DefaultJobConfig>> apiResponse = switch (httpMethod) {
                case Constants.PUT -> this.httpPutApiIntegrationService.putApiCallExecution(apiDetails, type);
                default -> throw new IllegalArgumentException("Invalid HTTP Method requested : " + httpMethod);
            };

            if (apiDetails.getMonitorJobExecution() != null) {
                if (apiResponse.getData() != null) {
                   apiResponse.setData( apiResponse.getData()
                            .parallelStream()
                            .map(defaultJobConfig -> {
                                defaultJobConfig.setJobType(type);
                                if (proceedIteration.test(defaultJobConfig.getJobStatus())) {
                                    return monitoringJobProcess.apply(apiDetails.getMonitorJobExecution(), defaultJobConfig);
                                }
                                return defaultJobConfig;
                            })
                .toList());;
                }
                throw new ApiSchdulerException("Response From Server is null for : " + type);
            }

            ApiResponseTransformer apiResponseTransformer =
                    TransformerProcessor.getResponseTransformer(apiDetails.getResponseTransformer());
            if (apiResponseTransformer != null) {
                apiResponse.getData().forEach(defaultJobConfig -> {
                    apiResponseTransformer.transformResponse(apiDetails, apiResponse.getData());
                });
            } else {
                log.info("No Response Transformer found for type: {}", type);
            }
        }
        throw new ApiSchdulerException("Api Details not found for type: " + type);
    };

    final private Function<String, List<DefaultJobConfig>> requestRouter = (type) -> {
        log.info("Request Router invoked for type: {}", type);

        if (abortApiConfigs.getApiConfigs().containsKey(type)) {
            log.info("Processing request for type: {}", type);
            List<DefaultJobConfig> list =  processJobRequest.apply(type);
            return list;
        }
        throw new ResourceNotFoundException("Invalid Job Type Requested " + type);
    };

    private final Function<String, Integer> processAbortApiRequest = (type) -> {
        log.info("Processing Abort API request for type: {}", type);
        List<DefaultJobConfig>  response = requestRouter.apply(type);
        
       // log.info("Response received: for type {} status {} response {}", type, response.getJobStatus(), response);
       // return (exitStatus.test(response) ? 0 : 1);
        return 0;
    };

    public Integer executeApiRequest(String type){
        return this.getProcessAbortApiRequest().apply(type);
    }
}
