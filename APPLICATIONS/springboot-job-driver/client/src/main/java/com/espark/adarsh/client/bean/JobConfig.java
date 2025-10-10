package com.espark.adarsh.client.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface JobConfig {

    String getJobId();

    String getJobName();

    OffsetDateTime getJobStartedOn();

    OffsetDateTime getJobEndedOn();

    String getJobStatus();

    String getJobMessage();

    String getJobType();

    void setJobType(String jobType);

    void setJobMessage(String jobMessage);

    void setJobStatus(String jobStatus);

}
