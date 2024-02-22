package com.espark.adarsh.condition.web;

import com.espark.adarsh.condition.model.ServiceOne;
import com.espark.adarsh.condition.model.ServiceTwo;

@org.springframework.context.annotation.Configuration
public class ConditionalBeanNotWebConfig {

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
    public ServiceOne serviceOne(){
        // will initiate if the context is
        // WebApplicationContext.
        return new ServiceOne();
    }

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication
    public ServiceTwo serviceTwo(){
        // will initiate if the context is not
        // WebApplicationContext.
        return new ServiceTwo();
    }
}
