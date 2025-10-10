package com.espark.adarsh.client.util.input;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Predicate;

import static com.espark.adarsh.client.util.Constants.*;

@Slf4j
@Getter
@Component
public class InputValidator {
    public Predicate<Map<String, String>> validateCreateInput = (inputMap) -> {
        return (!inputMap.isEmpty() &&
                inputMap.containsKey(OPERATION) &&
                inputMap.get(OPERATION).equals(CREATE) &&
                inputMap.containsKey(JOB_TYPE) &&
                inputMap.get(JOB_TYPE) != null);
    };

    public Predicate<Map<String, String>> validateStatusInput = (inputMap) -> {
        return (!inputMap.isEmpty() &&
                inputMap.containsKey(OPERATION) &&
                inputMap.get(OPERATION).equals(STATUS) &&
                inputMap.containsKey(JOB_TYPE) &&
                inputMap.get(JOB_TYPE) != null);
    };

    public Predicate<Map<String, String>> validateAbortInput = (inputMap) -> {
        return (!inputMap.isEmpty() &&
                inputMap.containsKey(OPERATION) &&
                inputMap.get(OPERATION).equals(ABORT) &&
                inputMap.containsKey(JOB_TYPE) &&
                inputMap.get(JOB_TYPE) != null);
    };
}
