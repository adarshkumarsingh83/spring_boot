package com.espark.adarsh.exception;

public class InvalidJobAbortRequest extends RuntimeException {
    public InvalidJobAbortRequest(String s) {
        super(s);
    }
}
