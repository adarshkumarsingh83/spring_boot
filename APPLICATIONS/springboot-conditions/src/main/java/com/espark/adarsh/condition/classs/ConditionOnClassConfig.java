package com.espark.adarsh.condition.classs;

import com.espark.adarsh.condition.model.ServiceOne;
import com.espark.adarsh.condition.model.ServiceTwo;
import com.espark.adarsh.condition.model.ServiceThree;

@org.springframework.context.annotation.Configuration
public class ConditionOnClassConfig {

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnClass(value={java.util.HashMap.class})
    public ServiceOne serviceOne(){
        // will get created as HashMap class is on the classpath
        return new ServiceOne();
    }

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnClass(name="com.espark.adarsh.condition.model.ServiceFour")
    public ServiceTwo serviceTwo(){
        // won't be created as ServiceFour class is not on classpath
        return new ServiceTwo();
    }

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnClass(value=com.espark.adarsh.condition.model.PrintService.class)
    public ServiceThree serviceThree(){
        // PrintService is on the classpath in the project.
        //So, ServiceThree's instance will be created.
        return new ServiceThree();
    }
    
}
