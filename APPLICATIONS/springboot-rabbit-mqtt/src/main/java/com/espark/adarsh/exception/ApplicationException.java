package com.espark.adarsh.exception;

public class ApplicationException extends Exception {
    String message;

    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
        this.message = message;
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
