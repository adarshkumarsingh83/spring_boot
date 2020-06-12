package com.espark.adarsh.exception;


public class ApplicationException extends RuntimeException {

    private String errorCode;
    private Object[] paramList = null;

    public ApplicationException() {
        super("exception in application");
    }

    public ApplicationException(Throwable throwable) {
        super("exception in application", throwable);
    }

    public ApplicationException(String errorCode, Object... param) {
        super("exception in application");
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
