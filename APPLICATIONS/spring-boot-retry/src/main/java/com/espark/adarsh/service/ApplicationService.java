package com.espark.adarsh.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApplicationService {

    RestTemplate restTemplate = new RestTemplate();

    public Map<String,String> wish(String type, String name){
        return restTemplate.getForObject("http://localhost:8080/message/"+name+"/"+type, HashMap.class);
    }
}
