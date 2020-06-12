package com.espark.adarsh.exception;

public class ApplicationException {

    String message;
    String cause;

    public ApplicationException(Exception e) {
        this.message = e.getMessage();
        this.cause=e.getCause().toString();
    }
}
