package com.espark.adarsh.exception;


public class ValidationException extends RuntimeException {

    private String code;
    private String message;

    public ValidationException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
