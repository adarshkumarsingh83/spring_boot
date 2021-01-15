package com.espark.adarsh.service;

import com.espark.adarsh.bean.EsparkServiceParam;

public interface EsparkService {

    public String doOperation(String name);

    default void setEsparkServiceParam(EsparkServiceParam esparkServiceParam){

    }
}
