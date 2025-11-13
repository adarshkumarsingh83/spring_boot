package com.espark.adarsh.client.util.input;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.espark.adarsh.client.util.Constants.*;

@Slf4j
@Getter
@Component
public class InputValidator {

    public Function<Map<String, String>, String> validateInput = (inputMap) -> {
        String key = "";
        if (this.getValidateCreateInput().test(inputMap)) {
            key = CREATE;
        } else if (this.getValidateAbortInput().test(inputMap)) {
            key = ABORT;
        } else if (this.getValidateStatusInput().test(inputMap)) {
            key = STATUS;
        } else {
            throw new IllegalArgumentException("Invalid Input Provided ");
        }
        return key;
    };

    private final Predicate<Map<String, String>> validateCreateInput = (inputMap) -> {
        return (!inputMap.isEmpty() &&
                inputMap.containsKey(OPERATION) &&
                inputMap.get(OPERATION).equals(CREATE) &&
                inputMap.containsKey(JOB_TYPE) &&
                inputMap.get(JOB_TYPE) != null);
    };

    private final Predicate<Map<String, String>> validateStatusInput = (inputMap) -> {
        return (!inputMap.isEmpty() &&
                inputMap.containsKey(OPERATION) &&
                inputMap.get(OPERATION).equals(STATUS) &&
                inputMap.containsKey(JOB_TYPE) &&
                inputMap.get(JOB_TYPE) != null);
    };

    private final Predicate<Map<String, String>> validateAbortInput = (inputMap) -> {
        return (!inputMap.isEmpty() &&
                inputMap.containsKey(OPERATION) &&
                inputMap.get(OPERATION).equals(ABORT) &&
                inputMap.containsKey(JOB_TYPE) &&
                inputMap.get(JOB_TYPE) != null);
    };
}
