package com.espark.adarsh.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties("espark")
@Getter
@Setter
public class MessageStore {
    private Map<String, String> messages;

    public String getMessage(String key) {
        if (messages != null && !messages.isEmpty()
                && key != null && !key.isEmpty()) {
            return messages.getOrDefault(key, null);
        }
        return null;
    }
}
