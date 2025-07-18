package com.espark.adarsh.bean;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransportStatus {
    private String id;
    private String from;
    private String destination;
    private String type;
    private String status;
    private String message;
    private LocalDateTime eventTime;
}
