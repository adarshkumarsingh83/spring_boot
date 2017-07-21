package com.espark.adarsh.bean;

public class ApplicationBean<T> {

    private T data;
    private String message;

    public ApplicationBean() {
    }

    public ApplicationBean(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
