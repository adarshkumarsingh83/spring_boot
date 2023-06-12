package com.espark.adarsh.exception;


import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeNotFoundException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    public EmployeeNotFoundException(String message, Long id) {
        super(message);
        extensions.put("EmployeeNotFoundException ", id);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }

    @Override
    public String toString() {
        return "EmployeeNotFoundException "+this.getMessage();
    }
}
