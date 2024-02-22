package com.espark.adarsh.condition.property;

import com.espark.adarsh.condition.model.ServiceOne;
import com.espark.adarsh.condition.model.ServiceTwo;
import com.espark.adarsh.condition.model.ServiceThree;

@org.springframework.context.annotation.Configuration
public class ConditionalBeanPropertyConfig {
    
    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnProperty(name="service.property"
            , havingValue="ServiceOne")
    public ServiceOne serviceOne(){
        return new ServiceOne();
    }

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnProperty(name="service.property"
            , havingValue="ServiceTwo")
    public ServiceTwo serviceTwo(){
        return new ServiceTwo();
    }
    
    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnProperty(name="service.property"
            , havingValue="ServiceThree"
            ,matchIfMissing=true)
    public ServiceThree serviceThree(){
        return new ServiceThree();
    }
}
