package com.espark.adarsh.service;

import com.espark.adarsh.bean.ApplicationMessageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

@Slf4j
public class EsparkServiceRemoteImpl implements EsparkService {

    @Autowired
    private Properties remoteProperties;

    @Autowired
    private ApplicationMessageBean applicationBean;

    @Override
    public String doOperation(String name) {
        log.info("label=EsparkService Remote name={}", name);
        return "Espark Remote "
                + name + " "
                + this.applicationBean.getMessage()
                + " " + remoteProperties.getProperty("remote.espark.msg");
    }

    public void setApplicationBean(ApplicationMessageBean applicationBean) {
        this.applicationBean = applicationBean;
    }

}
