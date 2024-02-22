package com.espark.adarsh.bean;

import org.springframework.http.HttpStatus;
import java.io.Serializable;

public class ResponseBean<T> implements Serializable{

    private T data;

    private int httpStatus;

    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
