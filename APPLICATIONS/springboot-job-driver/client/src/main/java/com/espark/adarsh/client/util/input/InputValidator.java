package com.espark.adarsh.client.util.input;

import com.espark.adarsh.client.exception.OperationConfigurationNotFound;
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
        if (this.getValidateExecuteInput().test(inputMap)) {
            key = EXECUTE;
        } else if (this.getValidateExecuteParallelInput().test(inputMap)) {
            key = EXECUTE_PARALLEL;
        } else if (this.getValidateExecuteSequentialInput().test(inputMap)) {
            key = EXECUTE_SEQUENTIAL;
        } else if (this.getValidateAbortInput().test(inputMap)) {
            key = ABORT;
        } else if (this.getValidateStatusInput().test(inputMap)) {
            key = STATUS;
        } else {
            throw new OperationConfigurationNotFound("Invalid Operation Input Provided ");
        }
        return key;
    };

    private final Predicate<Map<String, String>> validateExecuteParallelInput = (inputMap) -> {
        return (!inputMap.isEmpty() &&
                inputMap.containsKey(OPERATION) &&
                inputMap.get(OPERATION).equals(EXECUTE_PARALLEL) &&
                inputMap.containsKey(JOB_TYPE) &&
                inputMap.get(JOB_TYPE) != null);
    };

    private final Predicate<Map<String, String>> validateExecuteSequentialInput = (inputMap) -> {
        return (!inputMap.isEmpty() &&
                inputMap.containsKey(OPERATION) &&
                inputMap.get(OPERATION).equals(EXECUTE_SEQUENTIAL) &&
                inputMap.containsKey(JOB_TYPE) &&
                inputMap.get(JOB_TYPE) != null);
    };

    private final Predicate<Map<String, String>> validateExecuteInput = (inputMap) -> {
        return (!inputMap.isEmpty() &&
                inputMap.containsKey(OPERATION) &&
                inputMap.get(OPERATION).equals(EXECUTE) &&
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
