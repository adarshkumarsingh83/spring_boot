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
    String jobStatus;
    Boolean abortRequest;
    LocalDateTime expectedCompletion;
    LocalDateTime lastIteration;
    LocalDateTime completedOn;
    String jobMessage;

    public JobDetail() {
    }

    @Override
    public String toString() {
        return "JobDetail{" +
                "jobId='" + jobId + '\'' +
                ", jobName='" + jobName + '\'' +
                ", startedBy='" + startedBy + '\'' +
                ", startedOn=" + startedOn +
                ", status='" + jobStatus + '\'' +
                ", abortRequest=" + abortRequest +
                ", expectedCompletion=" + expectedCompletion +
                ", lastIteration=" + lastIteration +
                ", completedOn=" + completedOn +
                ", message='" + jobMessage + '\'' +
                '}';
    }
}
