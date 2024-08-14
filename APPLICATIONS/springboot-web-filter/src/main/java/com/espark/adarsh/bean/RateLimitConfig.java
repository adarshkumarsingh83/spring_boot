package com.espark.adarsh.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ConfigurationProperties("espark.api")
@Component
public class RateLimitConfig {
    boolean enable;
    List<ApiConfig> apiConfig;
}
