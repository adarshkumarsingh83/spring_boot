package com.espark.adarsh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(EsparkBeanRegistrationConfig.class)
public class ApplicationConfig {

}
