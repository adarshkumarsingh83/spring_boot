package com.espark.adarsh.bean;


import java.io.Serializable;

public class RequestBean <T> implements Serializable {

    T data;
    String batchId;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
}
