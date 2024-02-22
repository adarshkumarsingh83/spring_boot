package com.espark.adarsh.exception;

public class ValidationFailed extends Exception{

    private String errorCode;

    private Object[] paramList=null;

    public ValidationFailed() {
        super("Validation Failed");
    }

    public ValidationFailed(String errorCode,Object ...param) {
        super("Validation Failed");
        this.errorCode=errorCode;
        paramList=param;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getParamList() {
        return paramList;
    }
}
