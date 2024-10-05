package com.espark.adarsh.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Setter
@Getter
@Component
@ConditionalOnProperty(name = "application.espark.process.config.enable",havingValue = "true")
@ConfigurationProperties(prefix = "application.espark.process.config")
public class EsparkProcessConfig {
    Boolean enable;
    EsparkServiceMessage service;
}
