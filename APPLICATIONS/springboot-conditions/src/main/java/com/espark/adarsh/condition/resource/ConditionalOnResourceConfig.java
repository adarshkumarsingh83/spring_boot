package com.espark.adarsh.condition.resource;

import com.espark.adarsh.condition.model.ServiceOne;
import com.espark.adarsh.condition.model.ServiceTwo;

@org.springframework.context.annotation.Configuration
public class ConditionalOnResourceConfig {

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnResource(resources={"classpath:application.properties"})
    public ServiceOne serviceOne(){
        // will initiate as application.properties is in classpath.
        return new ServiceOne();
    }

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnResource(resources={"file:/usr/data.txt"})
    public ServiceTwo serviceTwo(){
        // will not initialize as the file is not
        // present in the given location.
        return new ServiceTwo();
    }
}
