package com.espark.adarsh.bean;


import java.io.Serializable;

public class ResponseBean<T> implements Serializable {

    T date;
    String message;
    String batchId;

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
}
