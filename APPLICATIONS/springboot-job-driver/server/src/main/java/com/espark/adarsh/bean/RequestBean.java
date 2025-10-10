package com.espark.adarsh.bean;

public class RequestBean <T>{

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

    public RequestBean<T> buildData(T data){
        this.data=data;
        return this;
    }
    public RequestBean<T> buildMessage(String message){
        this.message = message;
        return this;
    }
}
