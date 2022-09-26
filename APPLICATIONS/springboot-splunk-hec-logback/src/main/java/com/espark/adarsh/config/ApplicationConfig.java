package com.espark.adarsh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class ApplicationConfig {

    @Autowired
    Environment environment;

    @Autowired
    SplunkConfig splunkConfig;

    @PostConstruct
    public void init() {
        System.setProperty("APP_NAME", environment.getProperty("spring.application.name"));
        System.setProperty("SPLUNK_HOST", splunkConfig.getHost());
        System.setProperty("SPLUNK_URL", splunkConfig.getUrl());
        System.setProperty("SPLUNK_TOKEN", splunkConfig.getToken());
        System.setProperty("SPLUNK_INDEX", splunkConfig.getIndex());
        System.setProperty("SPLUNK_SOURCE", splunkConfig.getSource());
    }
}
