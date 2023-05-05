package com.espark.adarsh.exception;


import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class EmployeeNotFoundException extends RuntimeException implements GraphQLError {

    public EmployeeNotFoundException() {
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeNotFoundException(Throwable cause) {
        super(cause);
    }

    public EmployeeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }

    @Override
    public String toString() {
        return "EmployeeNotFoundException "+this.getMessage();
    }
}
