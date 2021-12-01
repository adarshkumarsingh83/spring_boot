package com.espark.adarsh.exception;

public class ServiceUnAvailableException extends RuntimeException {

    public ServiceUnAvailableException() {
    }

    public ServiceUnAvailableException(String message) {
        super(message);
    }

    public ServiceUnAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceUnAvailableException(Throwable cause) {
        super(cause);
    }

    public ServiceUnAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
