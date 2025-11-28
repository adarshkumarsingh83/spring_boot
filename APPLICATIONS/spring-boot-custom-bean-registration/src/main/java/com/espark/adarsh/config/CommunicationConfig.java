package com.espark.adarsh.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "espark.application")
public class CommunicationConfig {

    private Map<String,Communication> communication = new HashMap<>();

    @Getter
    @Setter
    static class Communication {
        private boolean enabled;
        private String name;

    }
}
