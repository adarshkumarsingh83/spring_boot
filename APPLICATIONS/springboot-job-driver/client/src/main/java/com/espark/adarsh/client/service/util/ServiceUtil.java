package com.espark.adarsh.client.service.util;

import com.espark.adarsh.client.bean.DefaultJobConfig;
import com.espark.adarsh.client.bean.JobConfig;
import com.espark.adarsh.client.util.Constants;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Predicate;

@Slf4j
@Getter
@Service
public class ServiceUtil {

    private final Predicate<JobConfig> executeExitStatus = (jobConfig) -> {
        return switch (jobConfig.getJobStatus()) {
            case Constants.COMPLETED -> true;
            case Constants.FAILED, Constants.ABORT -> false;
            default -> throw new IllegalStateException("Unexpected value: " + jobConfig.getJobStatus());
        };
    };

    private final Predicate<JobConfig> abortExitStatus = (jobConfig) -> {
        return switch (jobConfig.getJobStatus()) {
            case Constants.COMPLETED ,Constants.ABORT-> true;
            case Constants.FAILED -> false;
            default -> throw new IllegalStateException("Unexpected value: " + jobConfig.getJobStatus());
        };
    };

    private final BiFunction<String,String, DefaultJobConfig> defaultJobConfig = (jobName,jobStatus) -> {
        DefaultJobConfig defaultJobConfig = new DefaultJobConfig();
        defaultJobConfig.setJobName(jobName);
        defaultJobConfig.setJobStatus(jobStatus);
        return defaultJobConfig;
    };
}
