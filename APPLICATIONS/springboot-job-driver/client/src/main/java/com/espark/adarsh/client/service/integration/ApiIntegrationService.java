package com.espark.adarsh.client.service.integration;

import com.espark.adarsh.client.bean.AbstractApiDetails;
import com.espark.adarsh.client.bean.ApiResponse;

public interface ApiIntegrationService<T> {

    default ApiResponse<T> postApiCallExecution(AbstractApiDetails apiDetails, String jobName){
        return null;
    }

    default ApiResponse<T> getApiCallExecution(AbstractApiDetails apiDetails, String jobName){
        return null;
    }

    default ApiResponse<T> putApiCallExecution(AbstractApiDetails apiDetails, String jobName){
        return null;
    }

}
