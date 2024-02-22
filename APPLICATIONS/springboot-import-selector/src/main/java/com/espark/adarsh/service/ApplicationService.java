package com.espark.adarsh.service;

import com.espark.adarsh.bean.ApplicationMessageBean;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationService {

    @Autowired
    ApplicationMessageBean applicationMessageBean;

    public String getMessage(){
        return this.applicationMessageBean.getMessage();
    }

}
