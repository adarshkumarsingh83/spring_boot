package com.espark.adarsh.service;

import com.espark.adarsh.bean.EsparkServiceParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

@Slf4j
public class EsparkServiceRemoteImpl implements EsparkService {

    @Autowired
    private Properties remoteProperties;

    @Autowired
    private EsparkServiceParam esparkServiceParam;

    @Override
    public String doOperation(String name) {
        log.info("label=EsparkService Remote name={}", name);
        return "Espark Remote " + name + " " + this.esparkServiceParam.getDetails();
    }

    @Override
    public void setEsparkServiceParam(EsparkServiceParam esparkServiceParam) {
        this.esparkServiceParam = esparkServiceParam;
    }

}
