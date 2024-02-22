package com.espark.adarsh.condition.classs;

import com.espark.adarsh.condition.model.ServiceOne;
import com.espark.adarsh.condition.model.ServiceTwo;

@org.springframework.context.annotation.Configuration
public class ConditionOnMissingClassConfig {

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass(value={"com.espark.adarsh.condition.model.ServiceFour"})
    public ServiceOne serviceOne(){
        // will get created as ServiceFour class is missing
        // from classpath.
        return new ServiceOne();

    }

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass(value={"com.espark.adarsh.condition.model.ServiceThree"})
    public ServiceTwo serviceTwo(){
        // won't be created as ServiceThree is present in classpath.
        return new ServiceTwo();
    }
    
}
