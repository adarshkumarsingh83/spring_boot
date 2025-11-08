package com.espark.adarsh.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobConfig implements Serializable {

    String maxRunTime;
    Boolean exceptions;
    Boolean abort;
    List<String> conflict;
    String action;
    Integer waitTime;
    String message;

}
