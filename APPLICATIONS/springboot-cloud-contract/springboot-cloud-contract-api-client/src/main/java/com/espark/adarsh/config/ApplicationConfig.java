package com.espark.adarsh.config;

import com.espark.adarsh.service.EmployeeApiServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    EmployeeApiServiceClient employeeApiServiceClient(RestTemplate restTemplate) {
        return new EmployeeApiServiceClient(restTemplate);
    }
}
