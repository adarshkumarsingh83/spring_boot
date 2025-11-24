package com.espark.adarsh.exception.handler;

import com.espark.adarsh.exception.EsparkApiException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(EsparkApiException.class)
    public String handleEsparkApiException(EsparkApiException ex) {
        return "Espark API Error: " + ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex) {
        return "An error occurred: " + ex.getMessage();
    }
}
