package com.espark.adarsh.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "espark.default")
public class BatchConfigurationValues {

    private Value value;

    @Data
    static class Value {
        private String preTask;
        private String task;
        private String postTask;
    }
}
