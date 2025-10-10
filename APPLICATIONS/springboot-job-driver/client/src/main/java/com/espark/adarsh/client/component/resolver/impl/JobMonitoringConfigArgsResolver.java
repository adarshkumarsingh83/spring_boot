package com.espark.adarsh.client.component.resolver.impl;

import com.espark.adarsh.client.annotaton.ConfigArgumentResolver;
import com.espark.adarsh.client.bean.AbstractApiDetails;
import com.espark.adarsh.client.bean.JobConfig;
import com.espark.adarsh.client.component.resolver.ConfigArgumentsResolver;

@ConfigArgumentResolver(name="monitoring")
public class JobMonitoringConfigArgsResolver<T> implements ConfigArgumentsResolver<T> {

    final String id ="{job-id}";

    @Override
    public void resolvePathParam(AbstractApiDetails apiDetails, T config){
        String url = apiDetails.getApiUrl();
        if(url.contains(id)){
            url = url.replace((id),((JobConfig)config).getJobId());
            apiDetails.setApiUrl(url);
        }
    }
}
