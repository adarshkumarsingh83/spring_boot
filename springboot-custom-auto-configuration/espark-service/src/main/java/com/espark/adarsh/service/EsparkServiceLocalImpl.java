package com.espark.adarsh.service;

import com.espark.adarsh.bean.ApplicationMessageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

@Slf4j
public class EsparkServiceLocalImpl implements EsparkService {


    @Autowired
    private Properties localProperties;

    @Autowired
    private ApplicationMessageBean applicationMessageBean;

    @Override
    public String doOperation(String name) {
        log.info("label=EsparkService Local name={}", name);
        return "Espark Local "
                + name + " "
                + this.applicationMessageBean.getMessage()
                + " " + localProperties.getProperty("local.espark.msg");
    }

    @Override
    public void setApplicationMessageBean(ApplicationMessageBean applicationMessageBean) {
        this.applicationMessageBean = applicationMessageBean;
    }
}
