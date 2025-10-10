package com.espark.adarsh.client.component.transformer.impl;

import com.espark.adarsh.client.annotaton.RequestTransformer;
import com.espark.adarsh.client.bean.AbstractApiDetails;
import com.espark.adarsh.client.bean.DefaultJobConfig;
import com.espark.adarsh.client.component.resolver.ConfigArgumentsResolver;
import com.espark.adarsh.client.component.transformer.ApiRequestTransformer;
import com.espark.adarsh.client.util.resolver.ConfigArgsResolverProcessor;
import com.espark.adarsh.client.util.transformer.TransformerProcessor;

@RequestTransformer(name = "espark-job-monitoring-request-transformer")
public class JobMonitoringApiRequestTransformer<T> implements ApiRequestTransformer<T> {

    private final ConfigArgsResolverProcessor configArgsResolverProcessor;

    public JobMonitoringApiRequestTransformer(
            ConfigArgsResolverProcessor configArgsResolverProcessor) {
        this.configArgsResolverProcessor = configArgsResolverProcessor;
    }

    @Override
    public void transformRequest(AbstractApiDetails apiDetails, T config) {
        String resolver = apiDetails.getResolver();
        if (resolver != null && !resolver.isBlank()) {
            ConfigArgumentsResolver configArgumentsResolver =
                    configArgsResolverProcessor.getResolvers(resolver);
            DefaultJobConfig defaultJobConfig = (DefaultJobConfig) config;
            configArgumentsResolver.resolvePathParam(apiDetails, defaultJobConfig);
            configArgumentsResolver.resolveRequestParam(apiDetails, defaultJobConfig);
            configArgumentsResolver.resolveQueryParam(apiDetails, defaultJobConfig);
        }

        String transformer = apiDetails.getRequestTransformer();
        if (transformer != null && !transformer.isBlank()) {
            ApiRequestTransformer apiRequestTransformer =
                    TransformerProcessor.getRequestTransformer(transformer);
            DefaultJobConfig defaultJobConfig = (DefaultJobConfig) config;
            apiRequestTransformer.transformRequest(apiDetails, defaultJobConfig);
        }
    }
}
