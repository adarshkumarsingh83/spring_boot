package com.espark.adarsh.exception;

public class JobException extends RuntimeException{

    public JobException() {
    }

    public JobException(String message) {
        super(message);
    }

    public JobException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobException(Throwable cause) {
        super(cause);
    }

    public JobException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
