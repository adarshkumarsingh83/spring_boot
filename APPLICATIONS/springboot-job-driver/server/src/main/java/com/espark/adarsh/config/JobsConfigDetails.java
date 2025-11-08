package com.espark.adarsh.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@ConfigurationProperties("job.config")
public class JobsConfigDetails {

   private final Map<String, JobConfig> jobTypes = new HashMap<>();

}
