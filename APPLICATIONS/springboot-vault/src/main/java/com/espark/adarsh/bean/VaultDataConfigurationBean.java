package com.espark.adarsh.bean;

import com.espark.adarsh.config.VaultDataConfiguration;

import java.io.Serializable;

public class VaultDataConfigurationBean implements Serializable {


    String key;
    String value;
    String message;

    public VaultDataConfigurationBean() {
    }

    public VaultDataConfigurationBean(VaultDataConfiguration vaultDataConfiguration) {
        this.key = vaultDataConfiguration.getKey();
        this.value = vaultDataConfiguration.getValue();
        this.message = vaultDataConfiguration.getMessage();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
