package com.espark.adarsh.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    private static final Logger log = LoggerFactory.getLogger(ApplicationConfig.class);
    private RestTemplateBuilder restTemplateBuilder;

    public ApplicationConfig(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder.additionalInterceptors((request, body, execution) -> {
            log.info("ApplicationConfig::interceptor request {} currentThread {} "
                    , request.getHeaders().get("id"), Thread.currentThread().getName());
            ClientHttpResponse response = execution.execute(request, body);
            log.info("ApplicationConfig::interceptor response {} currentThread {} "
                    , request.getHeaders().get("id"), Thread.currentThread().getName());
            return response;
        }).build();
    }
}
