package com.espark.adarsh.client.util.input;

import com.espark.adarsh.client.exception.InSufficientArgumentsException;
import com.espark.adarsh.client.exception.InputTranslationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Getter
@Component
public class InputGenerator {

    public Function<String[], Map<String, String>> jsonInputGenerator = (String[] args) -> {
        Map<String, String> inputMap = new HashMap<>();
        if (args != null && args.length > 0) {
            try {
                inputMap = new ObjectMapper().readValue(args[1], new TypeReference<Map<String, String>>() {
                });
            } catch (Exception e) {
                log.error("Error while processing input arguments", e);
                throw new InputTranslationException("Error while processing input arguments", e);
            }
        }
        return inputMap;
    };

    public Function<String[], Map<String, String>> generateCmdLineInput = (String[] args) -> {
        if (args == null || args.length == 0) {
            throw new InSufficientArgumentsException("Insufficient arguments provided. Please provide a JSON string as the first argument.");
        } else {
            return jsonInputGenerator.apply(args);
        }
    };
}
