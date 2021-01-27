package com.espark.adarsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApplicationService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${service.api.url}")
    String apiUrl;


    public String getData() {
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(apiUrl, String.class);
        return (responseEntity.getStatusCode().is2xxSuccessful() ? responseEntity.getBody() : responseEntity.getStatusCode().toString());
    }

    public String setData(String data) {
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(apiUrl, "{'payload':" + data + "}", String.class);
        return (responseEntity.getStatusCode().is2xxSuccessful() ? responseEntity.getBody() : responseEntity.getStatusCode().toString());
    }
}
