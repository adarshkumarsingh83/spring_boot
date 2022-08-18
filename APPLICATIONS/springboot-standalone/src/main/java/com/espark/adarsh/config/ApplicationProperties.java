package com.espark.adarsh.config;

import com.espark.adarsh.exception.ApplicationException;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


@Data
@Component
@ConfigurationProperties(prefix = "app.config")
public class ApplicationProperties {

    private Map<String, String> message;

    public String getMessage(String key) {
        if (message != null) {
            if (message.containsKey(key)) {
                return message.get(key);
            } else {
                return null;
            }
        }
        throw new ApplicationException("Configuration not loaded ");
    }

}
