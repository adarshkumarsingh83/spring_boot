package com.espark.adarsh.service;

import com.espark.adarsh.config.ApplicationConfigProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


@Slf4j
@Service
public class SslIntegrationService {

    @Autowired
    ApplicationConfigProps applicationConfig;

    @Autowired
    RestTemplate restTemplate;

    public HashMap getData(String key) {
        log.info("label=SslIntegrationService getData() {}", key);
        String url = applicationConfig.getServiceHost()+applicationConfig.getServiceUrl().get(key);
        ResponseEntity<HashMap> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, HashMap.class);
        return response.getBody();
    }

    public List<String> getServiceKey() {
        log.info("label=SslIntegrationService getServiceKey() ");
        return new LinkedList<>(applicationConfig.getServiceUrl().keySet());
    }
}
