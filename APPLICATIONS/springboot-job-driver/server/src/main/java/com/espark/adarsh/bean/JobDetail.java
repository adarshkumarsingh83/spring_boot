package com.espark.adarsh.bean;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JobDetail {

    String jobId;
    String jobName;
    String startedBy;
    LocalDateTime startedOn;
    String status;
    Boolean abortRequest;
    LocalDateTime expectedCompletion;
    LocalDateTime lastIteration;
    String message;

    public JobDetail() {
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
