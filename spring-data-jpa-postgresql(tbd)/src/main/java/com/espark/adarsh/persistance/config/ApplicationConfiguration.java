package com.espark.adarsh.persistance.config;

import com.codahale.metrics.health.HealthCheckRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    HealthCheckRegistry dropWizardHealthCheckRegistry() {
        return new HealthCheckRegistry();
    }

}
