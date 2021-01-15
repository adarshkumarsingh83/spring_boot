package com.espark.adarsh.bean;

import java.io.Serializable;

public class EsparkRequestBean implements Serializable{

    private AbstractBean data;

    public AbstractBean getData() {
        return data;
    }

    public void setData(AbstractBean data) {
        this.data = data;
    }
}
