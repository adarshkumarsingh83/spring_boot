package com.espark.adarsh.client.component.transformer.impl;

import com.espark.adarsh.client.annotaton.RequestTransformer;
import com.espark.adarsh.client.bean.AbstractApiDetails;
import com.espark.adarsh.client.bean.DefaultJobConfig;
import com.espark.adarsh.client.component.transformer.ApiResponseTransformer;
import com.espark.adarsh.client.util.transformer.TransformerProcessor;

@RequestTransformer(name = "espark-job-response-transformer")
public class JobApiResponseTransformer<T> implements ApiResponseTransformer<T> {


    @Override
    public void transformResponse(AbstractApiDetails apiDetails, T config) {
        String transformer = apiDetails.getResponseTransformer();
        if (transformer != null && !transformer.isBlank()) {
            ApiResponseTransformer responseTransformers = TransformerProcessor.getResponseTransformer(transformer);
            DefaultJobConfig defaultJobConfig = (DefaultJobConfig) config;
            responseTransformers.transformResponse(apiDetails, defaultJobConfig);
        }
    }
}
