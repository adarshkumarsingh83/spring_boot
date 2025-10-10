package com.espark.adarsh.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@Slf4j
@Configuration
public class ApiRestTemplateFactory {

    @Value("${espark.job.scheduler.enable-interceptor}")
    private Boolean interceptorEnabled;

    private RestTemplateBuilder restTemplateBuilder;

    public ApiRestTemplateFactory(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public Function<String, RestTemplate> restTemplate = (key) -> {
        if (interceptorEnabled) {
            restTemplateBuilder.additionalInterceptors((request, body, execution) -> {
                log.info("ApiRestTemplateFactory::restTemplate interceptor execution  ");
                return execution.execute(request, body);
            });
        }
        return restTemplateBuilder.build();
    };
}
