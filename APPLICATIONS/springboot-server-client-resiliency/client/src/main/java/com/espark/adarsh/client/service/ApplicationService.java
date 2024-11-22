package com.espark.adarsh.client.service;

import com.espark.adarsh.client.config.ServerApiConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;
import java.util.function.Supplier;


@Service
public class ApplicationService {

    ServerApiConfig serverApiConfig;

    RestTemplate restTemplate;

    public ApplicationService(ServerApiConfig serverApiConfig, RestTemplate restTemplate) {
        this.serverApiConfig = serverApiConfig;
        this.restTemplate = restTemplate;
    }


    public Supplier<HttpEntity<String>> httpEntitySupplier=()->{
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT,"application/json");
       return  new HttpEntity<>(httpHeaders);
    };


    public Function<String, ResponseEntity<String>> processRequest = (request) -> {
        ResponseEntity<String> responseEntity=null;
        try {
            HttpEntity<String> httpEntity = httpEntitySupplier.get();
            responseEntity = restTemplate.exchange(serverApiConfig.getUrl() + serverApiConfig.getApi() + request, HttpMethod.GET,
                    httpEntity, String.class);
        } catch (HttpClientErrorException e) {
           return  ResponseEntity.status(400).body(e.getStatusText() + " Exception in Server "+e.getMessage());
        } catch (HttpServerErrorException e){
            return  ResponseEntity.status(500).body(e.getStatusText() + " Exception in Server "+e.getMessage());
        }
        return ResponseEntity.status(200).body(responseEntity.getBody());
    };




}
