package com.espark.adarsh.bean;

import java.io.Serializable;

public class MessageBean<T> implements Serializable {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "data=" + data.toString() +
                '}';
    }
}
