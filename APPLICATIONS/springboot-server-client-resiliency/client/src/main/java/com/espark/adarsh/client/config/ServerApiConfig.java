package com.espark.adarsh.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("app.server")
public class ServerApiConfig {
    String url;
    String api;
}
