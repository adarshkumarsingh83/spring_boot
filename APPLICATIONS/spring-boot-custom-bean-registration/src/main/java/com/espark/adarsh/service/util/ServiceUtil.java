package com.espark.adarsh.service.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Getter
@Component
public class ServiceUtil {

    private Function<String, String> wishFunction =
            (name) -> {
                String response = "Hello, " + name + "! Welcome to Espark Application.";
                log.info("Response from ServiceUtil : {}", response);
                return response;
            };
}
