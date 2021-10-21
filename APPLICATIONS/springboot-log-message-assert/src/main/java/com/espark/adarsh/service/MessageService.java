package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageService {

    public final static String template = "Greeting from Message Service to ";

    public String getMessage(String name) {
        log.info(template + name);
        return template + name;
    }

    public static String getName(){
        return "adarsh";
    }
}
