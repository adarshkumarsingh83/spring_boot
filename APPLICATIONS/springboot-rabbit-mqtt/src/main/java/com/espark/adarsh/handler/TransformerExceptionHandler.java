package com.espark.adarsh.handler;

import com.espark.adarsh.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class TransformerExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public @ResponseBody
    Map<String, String> respondApplicationException(ApplicationException applicationException) {
        log.error("TransformerExceptionHandler::respondApplicationException() {}", applicationException.getMessage());
        return new HashMap<String, String>() {
            {
                put("message", applicationException.getMessage());
            }
        };
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    Map<String, String> respondException(Exception exception) {
        log.error("TransformerExceptionHandler::respondException() {}", exception.getMessage());
        return new HashMap<String, String>() {
            {
                put("message", exception.getMessage());
            }
        };
    }
}
