package com.espark.adarsh.bean;

import java.util.LinkedList;
import java.util.List;

public class InBean<T> {

    private List<T> data=new LinkedList<>();
    private String fieldName;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
