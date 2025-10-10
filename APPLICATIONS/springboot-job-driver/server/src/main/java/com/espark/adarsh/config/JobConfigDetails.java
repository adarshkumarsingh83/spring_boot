package com.espark.adarsh.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties("job.config")
public class JobConfigDetails {

   private  Map<String,JobDetails> jobTypes= new HashMap<>();

    public Map<String, JobDetails> getJobTypes() {
        return jobTypes;
    }

    public void setJobTypes(Map<String, JobDetails> jobTypes) {
        this.jobTypes = jobTypes;
    }
}
