package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ApiService {

    private RestTemplate restTemplate;
    private List<Map<String,String>> store = new LinkedList<>();

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async("apiTaskExecutor")
    public void callApiFromTeak1(String name, int count) {
        log.info("ApiService.callApiFromTeak1() : name : {} , count : {} ", name, count);
        for(int i=0;i<count;i++) {
            log.info("ApiService.callApiFromTeak1() : processing count : {} ", i);
            Map<String,String> response = this.restTemplate.getForObject("http://localhost:8090/api/wish/{name}", Map.class, name);
            store.add(response);
            log.info("ApiService.callApiFromTeak1() : response for count {} : {} ", i, response);
        }
        log.info("ApiService.callApiFromTeak1() : response : {} ", store);
    }

    @Async("apiTaskExecutor")
    public void callApiFromTeak2(String name, int count) {
        log.info("ApiService.callApiFromTeak2() : name : {} , count : {} ", name, count);
        for(int i=0;i<count;i++) {
            log.info("ApiService.callApiFromTeak2() : processing count : {} ", i);
            Map<String,String> response = this.restTemplate.getForObject("http://localhost:8090/api/wish/{name}", Map.class, name);
            store.add(response);
            log.info("ApiService.callApiFromTeak2() : response for count {} : {} ", i, response);
        }
        log.info("ApiService.callApiFromTeak2() : response : {} ", store);
    }


}
