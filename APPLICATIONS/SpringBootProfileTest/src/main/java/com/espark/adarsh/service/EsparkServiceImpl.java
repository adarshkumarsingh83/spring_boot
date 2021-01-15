package com.espark.adarsh.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile(value = {"development", "testing", "production"})
@Service("esparkService")
public class EsparkServiceImpl implements EsparkService {

    @Value("${message}")
    private String message;

    @Override
    public String getWishMessage() {
        return message + " " + System.getProperty("user.name");
    }
}
