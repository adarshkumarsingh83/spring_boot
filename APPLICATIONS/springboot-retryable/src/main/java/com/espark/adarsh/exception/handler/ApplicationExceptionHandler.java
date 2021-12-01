package com.espark.adarsh.exception.handler;

import com.espark.adarsh.exception.ServiceUnAvailableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ServiceUnAvailableException.class)
    public @ResponseBody
    Map<String, String> remoteServiceNotAvailableException(ServiceUnAvailableException serviceUnAvailableException) {
        return new HashMap<String, String>() {
            {
                put("message", serviceUnAvailableException.getMessage());
            }
        };
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    Map<String, String> exception(Exception exception) {
        return new HashMap<String, String>() {
            {
                put("message", exception.getMessage());
            }
        };
    }
}
