package com.espark.adarsh.config;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnRequestJobConfig implements Serializable {

    String maxRunTime;
    Boolean exceptions;
    Boolean abort;
    List<String> conflict;
    String action;
    Integer waitTime;
    String message;
    OrphanJobConfig orphan;


    @Getter
    @Setter
    public static final class OrphanJobConfig {
        Integer maxGraceTime;
        String action;
    }

}
