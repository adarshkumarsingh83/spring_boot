package com.espark.adarsh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "espark.config")
public class ApplicationProps {
    private String apiUrl;
    private Integer requestCount;
}
