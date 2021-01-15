package com.espark.adarsh.bean;


public class EsparkServiceParamImpl implements EsparkServiceParam {

    private String type;
    private String message;

    public EsparkServiceParamImpl(String message, String type) {
        this.message = message;
        this.type = type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDetails() {
        return message + " from " + type;
    }
}
