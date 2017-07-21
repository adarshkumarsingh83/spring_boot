package com.espark.adarsh.configuration.mvc;
import com.espark.adarsh.configuration.security.SecurityConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Import({ SecurityConfiguration.class })
public class ApplicationConfiguration {
} 