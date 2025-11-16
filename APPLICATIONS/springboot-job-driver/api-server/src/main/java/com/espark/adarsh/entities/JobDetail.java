package com.espark.adarsh.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "JOB_DETAILS")
public class JobDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobId;
    private String jobName;
    private String startedBy;
    private LocalDateTime startedOn;
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;
    private Boolean abortRequest;
    private LocalDateTime expectedCompletion;
    private LocalDateTime lastIteration;
    private LocalDateTime completedOn;
    private String jobMessage;

}
