package com.espark.adarsh.bean;

public class ResponseBean<T> {

    private T data;

    public ResponseBean(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
