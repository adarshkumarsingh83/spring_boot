package com.espark.adarsh.service;

import com.espark.adarsh.service.config.EsparkProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EsparkService {

    EsparkProperties esparkProperties;

    public String getMessage(){
        return this.esparkProperties.getMessage();
    }

}
