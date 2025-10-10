package com.espark.adarsh.bean;

public class ResponseBean<T>{
    T data;
    String message;

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

    public ResponseBean<T> buildData(T data){
        this.data=data;
        return this;
    }
    public ResponseBean<T> buildMessage(String message){
        this.message = message;
        return this;
    }
}
