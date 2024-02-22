package com.espark.adarsh.bean;

import java.io.Serializable;

public class EsparkResponseBean implements Serializable{

    private AbstractBean data;
    private String message;

    public AbstractBean getData() {
        return data;
    }

    public void setData(AbstractBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
