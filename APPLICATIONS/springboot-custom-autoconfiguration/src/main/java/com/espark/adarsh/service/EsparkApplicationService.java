package com.espark.adarsh.service;


import com.espark.adarsh.config.EsparkServiceMessage;

import java.util.function.Function;

public class EsparkApplicationService {

    EsparkServiceMessage esparkServiceMessage;

    public EsparkApplicationService(EsparkServiceMessage esparkServiceMessage) {
        this.esparkServiceMessage = esparkServiceMessage;
    }

    public Function<String,String> displayGreeting = (name)->{
        return this.esparkServiceMessage.getMessage()+" "+name;
    };
}
