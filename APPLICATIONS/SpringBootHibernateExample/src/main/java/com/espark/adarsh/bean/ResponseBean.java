package com.espark.adarsh.bean;

import java.io.Serializable;

/**
 * Created by Adarsh on 1/28/16.
 */
public class ResponseBean<T> implements Serializable{
    private T data;
    private String message;

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
