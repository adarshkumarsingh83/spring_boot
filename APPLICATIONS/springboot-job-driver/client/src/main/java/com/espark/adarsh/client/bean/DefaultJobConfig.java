package com.espark.adarsh.client.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.time.OffsetDateTime;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultJobConfig implements JobConfig {

    String jobId;
    String jobName;
    OffsetDateTime jobStartedOn;
    OffsetDateTime jobEndedOn;
    String jobStatus;
    String jobMessage;
    String jobType;

    @Override
    public String getJobId() {
        return this.jobId;
    }

    @Override
    public String getJobName() {
        return this.jobName;
    }

    @Override
    public OffsetDateTime getJobStartedOn() {
        return this.jobStartedOn;
    }

    @Override
    public OffsetDateTime getJobEndedOn() {
        return this.jobEndedOn;
    }

    @Override
    public String getJobStatus() {
        return this.jobStatus;
    }

    @Override
    public String getJobMessage() {
        return this.jobMessage;
    }

    @Override
    public String getJobType() {
        return this.jobType;
    }

    @Override
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    @Override
    public void setJobMessage(String jobMessage) {
        this.jobMessage = jobMessage;
    }

    @Override
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
}
