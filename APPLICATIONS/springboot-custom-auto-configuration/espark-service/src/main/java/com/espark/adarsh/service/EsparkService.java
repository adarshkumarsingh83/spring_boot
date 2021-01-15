package com.espark.adarsh.service;

import com.espark.adarsh.bean.ApplicationMessageBean;

public interface EsparkService {

    public String doOperation(String name);

    default void setApplicationMessageBean(ApplicationMessageBean applicationMessageBean){

    }
}
