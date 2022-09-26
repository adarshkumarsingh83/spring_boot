package com.espark.adarsh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("spring.splunk")
public class SplunkConfig {

    String host;
    String url;
    String token;
    String index;
    String source;
    String sourceType;
}
