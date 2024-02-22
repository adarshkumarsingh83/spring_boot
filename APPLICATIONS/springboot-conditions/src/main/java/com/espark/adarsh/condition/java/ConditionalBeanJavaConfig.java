package com.espark.adarsh.condition.java;

import com.espark.adarsh.condition.model.ServiceOne;
import com.espark.adarsh.condition.model.ServiceTwo;
import org.springframework.boot.system.JavaVersion;

@org.springframework.context.annotation.Configuration
public class ConditionalBeanJavaConfig {

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnJava(value=JavaVersion.EIGHT)
    public ServiceOne serviceOne(){
        return new ServiceOne();
    }

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnJava(value= JavaVersion.NINE
            ,range= org.springframework.boot.autoconfigure.condition.ConditionalOnJava.Range.EQUAL_OR_NEWER)
    public ServiceTwo serviceTwo(){
        return new ServiceTwo();
    }
}
