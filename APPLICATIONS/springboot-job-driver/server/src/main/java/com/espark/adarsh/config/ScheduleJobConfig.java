package com.espark.adarsh.config;


import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties("job.config")
public class ScheduleJobConfig {

    private Map<String,ScheduleJob> onScheduleJobTypes = new HashMap<>();

    @Getter
    @Setter
    public static final class ScheduleJob {
        String corn;
        List<String> jobTypes;
    }
}
