package com.espark.adarsh.bean;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JobConfig {

    String jobId;
    String jobName;
    String startedBy;
    String startedOn;
    String status;
    Boolean abortRequest;
    LocalDateTime expectedCompletion;
    String message;

    public JobConfig() {
    }

    @Override
    public String toString() {
        return "JobConfig{" +
                "jobId='" + jobId + '\'' +
                ", jobName='" + jobName + '\'' +
                ", startedBy='" + startedBy + '\'' +
                ", startedOn='" + startedOn + '\'' +
                ", status='" + status + '\'' +
                ", expectedCompletion=" + expectedCompletion +
                ", message='" + message + '\'' +
                '}';
    }
}
