package com.espark.adarsh.config;

import com.espark.adarsh.integration.EmployeeApiIntegrationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(EmployeeApiIntegrationService.class)
public class ApplicationConfig {
}
