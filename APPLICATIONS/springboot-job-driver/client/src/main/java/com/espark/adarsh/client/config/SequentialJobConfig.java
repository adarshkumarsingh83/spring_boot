package com.espark.adarsh.client.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "espark.sequential.job.scheduler")
public class SequentialJobConfig {

    private Map<String, List<String>> executionConfig;

}
