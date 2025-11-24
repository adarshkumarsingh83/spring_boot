package com.espark.adarsh.exception;

public class EsparkApiException extends RuntimeException {

    public EsparkApiException(String s, Exception e) {
        super(s, e);
    }
}
