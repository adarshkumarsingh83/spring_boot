package com.espark.adarsh.client.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "espark.parallel.job.scheduler")
public class ParallelJobConfig {

    private Map<String, List<JobDetails>> executionConfig;

    @Getter
    @Setter
    public static class JobDetails {
        private String name;
        private boolean parallelExecution;
        private List<String> dependencies = new ArrayList<>();
    }
}
