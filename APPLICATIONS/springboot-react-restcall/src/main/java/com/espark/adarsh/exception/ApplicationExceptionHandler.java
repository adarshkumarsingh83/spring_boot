package com.espark.adarsh.exception;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ApplicationExceptionHandler
        extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class })
    protected org.springframework.http.ResponseEntity<Object> handleConflict(
            RuntimeException ex, org.springframework.web.context.request.WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new org.springframework.http.HttpHeaders(), org.springframework.http.HttpStatus.CONFLICT, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value
            = { ResourceNotFound.class })
    protected org.springframework.http.ResponseEntity<Object> handleResourceNotFound(ResourceNotFound resourceNotFound,org.springframework.web.context.request.WebRequest request) {
        return handleExceptionInternal(resourceNotFound, "Request Resource Not Found ",
                new org.springframework.http.HttpHeaders(), org.springframework.http.HttpStatus.NOT_FOUND, request);
    }
}
