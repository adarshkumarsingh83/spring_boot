package com.espark.adarsh.client.service;

import com.espark.adarsh.client.annotaton.ApiExecution;
import com.espark.adarsh.client.bean.AbstractApiDetails;
import com.espark.adarsh.client.bean.ApiResponse;
import com.espark.adarsh.client.bean.DefaultJobConfig;
import com.espark.adarsh.client.component.transformer.ApiRequestTransformer;
import com.espark.adarsh.client.config.StatusJobConfigs;
import com.espark.adarsh.client.exception.ApiOperationException;
import com.espark.adarsh.client.exception.ResourceNotFoundException;
import com.espark.adarsh.client.service.integration.HttpPostApiIntegrationServiceImpl;
import com.espark.adarsh.client.util.Constants;
import com.espark.adarsh.client.util.transformer.TransformerProcessor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Getter
@ApiExecution(name = Constants.STATUS)
public class JobStatusApiExecutionService implements ApiExecutionService {

    private StatusJobConfigs statusJobConfigs;

    private TransformerProcessor transformerProcessor;

    private HttpPostApiIntegrationServiceImpl<List<DefaultJobConfig>> httpPostApiIntegrationService;

    public JobStatusApiExecutionService(StatusJobConfigs statusJobConfigs,
                                        TransformerProcessor transformerProcessor,
                                        HttpPostApiIntegrationServiceImpl<List<DefaultJobConfig>> httpPostApiIntegrationService) {
        this.statusJobConfigs = statusJobConfigs;
        this.transformerProcessor = transformerProcessor;
        this.httpPostApiIntegrationService = httpPostApiIntegrationService;
    }

    private final Consumer<List<DefaultJobConfig>> displayDataReport = (jobConfigs) -> {
        if (jobConfigs != null && !jobConfigs.isEmpty()) {
            jobConfigs.forEach(jobConfig -> log.info("Job Config: {}", jobConfig));
        } else {
            log.info("No Job Configs found.");
        }
    };

    final private Function<String, ApiResponse<List<DefaultJobConfig>>> requestRouter = (type) -> {
        log.info("Request Router invoked for type: {}", type);
        AbstractApiDetails abstractApiDetails = this.statusJobConfigs.getApiConfigs().get(type);
        if (abstractApiDetails != null) {
            log.info("processing for status request for type: {}", type);
            ApiRequestTransformer apiResponseTransformer = TransformerProcessor.getRequestTransformer(abstractApiDetails.getRequestTransformer());
            if (apiResponseTransformer != null) {
                DefaultJobConfig jobConfig = new DefaultJobConfig();
                apiResponseTransformer.transformRequest(abstractApiDetails, jobConfig);
            }
            abstractApiDetails.setParameterizedTypeReference(new ParameterizedTypeReference<List<ApiResponse<DefaultJobConfig>>>() {
            });
            ApiResponse<List<DefaultJobConfig>> response = httpPostApiIntegrationService
                    .postApiCallExecution(abstractApiDetails, "job-status");
            return response;
        }
        throw new ResourceNotFoundException("Invalid Job Type Requested " + type);
    };

    public Function<String, Integer> processStatusApiRequest = (type) -> {
        log.info("Processing API request for type: {}", type);
        ApiResponse<List<DefaultJobConfig>> response = requestRouter.apply(type);
        if (response != null && response.getStatus()) {
            log.info("API request processed successfully for type: {}", type);
            displayDataReport.accept(response.getData());
            return (response.getStatus() ? 0 : 1);
        }
        throw new ApiOperationException("Failed to process API request for type: " + type);
    };

    @Override
    public Integer executeApiRequest(String type){
        return this.getProcessStatusApiRequest().apply(type);
    }
}
