package com.espark.adarsh.condition.web;

import com.espark.adarsh.condition.model.ServiceOne;
import com.espark.adarsh.condition.model.ServiceTwo;

@org.springframework.context.annotation.Configuration
public class ConditionalBeanWebConfig {

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
    public ServiceOne serviceOne(){
        return new ServiceOne();
    }

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication
    public ServiceTwo serviceTwo(){
        return new ServiceTwo();
    }
}
