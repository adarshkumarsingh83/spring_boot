package com.espark.adarsh.service;

import com.espark.adarsh.bean.EsparkServiceParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

@Slf4j
public class EsparkServiceLocalImpl implements EsparkService {


    @Autowired
    private Properties localProperties;

    @Autowired
    private EsparkServiceParam esparkServiceParam;

    @Override
    public String doOperation(String name) {
        log.info("label=EsparkService Local name={}", name);
        return "Espark Local " + name + " " + this.esparkServiceParam.getDetails();
    }

    @Override
    public void setEsparkServiceParam(EsparkServiceParam esparkServiceParam) {
        this.esparkServiceParam = esparkServiceParam;
    }
}
