package com.espark.adarsh.util;



import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilterUpdateUtil {

    private static final String SEPARATOR_PATTERN = "\\|\\|";
    public static final String TASK = "task";
    public static final String TASK_NAME_UPDATE = "nameUpdate";
    public static final String TASK_VALUE_UPDATE = "valueUpdate";

    public static Map<String, JobParameter> getJobParameters(String params) {
        List<String> commandParams = Arrays.asList(params.split(SEPARATOR_PATTERN));
        String taskName = commandParams.get(0);
        log.info("commandParams size {} | values {}", commandParams.size(), commandParams);

        if ((TASK_NAME_UPDATE.equalsIgnoreCase(taskName) && commandParams.size() != 3)
                || (TASK_VALUE_UPDATE.equalsIgnoreCase(taskName) && commandParams.size() != 4)) {
            throw new RuntimeException("Invalid Number of Arguments");
        }

        Map<String, JobParameter> parameters = new LinkedHashMap<>();
        parameters.put(TASK, new JobParameter(taskName));
        return parameters;
    }
}
