package com.espark.adarsh.service;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EsparkServiceDefaultImpl implements EsparkService {

    @Override
    public String doOperation(String name) {
        log.info("label=EsparkService Default name={}", name);
        return "Espark Default " + name;
    }
}
