package com.espark.adarsh.configuration;

import com.espark.adarsh.configuration.annotation.EnableConfiguration;
import com.espark.adarsh.service.ApplicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfiguration("remote")
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    @Bean
    public ApplicationService getApplicationService() {
        return new ApplicationService();
    }
}
