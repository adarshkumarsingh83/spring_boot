package com.espark.adarsh.exception;

public class ResourceNotFound extends Exception{

    public ResourceNotFound() {
        super("Resource Not Found");
    }

    public ResourceNotFound(String message) {
        super(message);
    }
}
