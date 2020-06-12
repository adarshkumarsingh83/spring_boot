package com.espark.adarsh.config;

import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public RestartEndpoint restartEndpoint(){
        return new RestartEndpoint();
    }
}
