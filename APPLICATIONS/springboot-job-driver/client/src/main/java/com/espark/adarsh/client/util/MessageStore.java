package com.espark.adarsh.client.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
@ConfigurationProperties(prefix = "espark.message")
public class MessageStore {
    private Map<String, String> store;

    public Function<String, String> message = (msgKey) -> {
        log.info("MessageStore::message {}", msgKey);
        if (store != null && !store.isEmpty() && msgKey != null && !msgKey.isEmpty()) {
            return store.getOrDefault(msgKey, null);
        }
        return null;
    };
}
