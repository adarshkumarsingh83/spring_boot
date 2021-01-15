package com.espark.adarsh.client;

import org.springframework.stereotype.Component;

@Component
public class ClientFallback implements ClientService{

    @Override
    public String greeting(String username){
            return String.format("Hello From Fallback %s!\n", username);
    }
}
