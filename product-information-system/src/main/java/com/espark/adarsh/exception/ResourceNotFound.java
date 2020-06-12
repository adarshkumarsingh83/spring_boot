package com.espark.adarsh.exception;


public class ResourceNotFound extends Exception {

    private String errorCode;
    private Object[] paramList = null;

    public ResourceNotFound() {
        super("Resource Not Found");
    }

    public ResourceNotFound(String errorCode, Object... param) {
        super("Resource Not Found");
        this.errorCode = errorCode;
        paramList = param;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getParamList() {
        return paramList;
    }
}
