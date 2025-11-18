package com.espark.adarsh.service;

import com.espark.adarsh.annotation.EsparkQualifier2;
import org.springframework.stereotype.Service;

@Service
@EsparkQualifier2
public class EsparkService2Impl implements EsparkService{

    public String getServiceName(){
        return  "Espark Service2 Supplier Implementation";
    };
}
