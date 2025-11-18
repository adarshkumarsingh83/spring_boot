package com.espark.adarsh.service;

import com.espark.adarsh.annotation.EsparkQualifier1;
import org.springframework.stereotype.Service;

@Service
@EsparkQualifier1
public class EsparkService1Impl implements EsparkService{

    @Override
    public String getServiceName(){
        return  "Espark Service1 Supplier Implementation";
    };
}
