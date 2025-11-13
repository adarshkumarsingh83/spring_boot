package com.espark.adarsh.client.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultJobConfig implements JobConfig {

    String jobId;
    String jobName;
    LocalDateTime jobStartedOn;
    LocalDateTime jobEndedOn;
    LocalDateTime lastIteration;
    LocalDateTime completedOn;
    String jobStatus;
    String jobMessage;
    String jobType;
    String startedBy;

    @Override
    public String getJobId() {
        return this.jobId;
    }

    @Override
    public String getJobName() {
        return this.jobName;
    }

    @Override
    public LocalDateTime getJobStartedOn() {
        return this.jobStartedOn;
    }

    @Override
    public LocalDateTime getJobEndedOn() {
        return this.jobEndedOn;
    }

    @Override
    public String getJobStatus() {
        return this.jobStatus;
    }

    @Override
    public String getStartedBy() {
        return startedBy;
    }

    @Override
    public LocalDateTime getCompletedOn(){
       return this.completedOn;
    }

    @Override
    public LocalDateTime getLastIteration() {
        return lastIteration;
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
