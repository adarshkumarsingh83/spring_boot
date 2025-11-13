package com.espark.adarsh.client.component.transformer.impl;

import com.espark.adarsh.client.annotaton.RequestTransformer;
import com.espark.adarsh.client.bean.AbstractApiDetails;
import com.espark.adarsh.client.bean.DefaultJobConfig;
import com.espark.adarsh.client.component.resolver.ConfigArgumentsResolver;
import com.espark.adarsh.client.component.transformer.ApiRequestTransformer;
import com.espark.adarsh.client.util.resolver.ConfigArgsResolverProcessor;
import com.espark.adarsh.client.util.transformer.TransformerProcessor;

@RequestTransformer(name = "espark-job-request-transformer")
public class JobApiRequestTransformer<T> implements ApiRequestTransformer<T> {

    @Override
    public void transformRequest(AbstractApiDetails apiDetails, T config) {
        String transformer = apiDetails.getRequestTransformer();
        if (transformer != null && !transformer.isBlank()) {

        }
    }
}
