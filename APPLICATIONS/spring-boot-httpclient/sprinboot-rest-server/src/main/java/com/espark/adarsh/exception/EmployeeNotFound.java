package com.espark.adarsh.exception;

public class EmployeeNotFound extends RuntimeException {
    public EmployeeNotFound(String s) {
        super(s);
    }
}
