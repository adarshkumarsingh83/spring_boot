package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ApplicationService {

    RestTemplate restTemplate = new RestTemplate();


    // Backoff is used to define the delay time between each retry call.
    // multiplier is used to increase delay time 2 times after each retry call.
    @Retryable(maxAttempts = 2, backoff = @Backoff(delay = 1000, multiplier = 2))
    public Map<String,String> wish(String type, String name){
        log.info("request for remote server ");
        return restTemplate.getForObject("http://localhost:8080/message/"+name+"/"+type, HashMap.class);
    }

    @Recover
    public Map<String,String> backupRecoverWish(String type, String name){
        log.info("backup recover executed ");
        return new HashMap<String,String>(){
            {
                put("msg","welcome to espark locally");
                put("name",name);
            }
        };
    }

}
