package com.espark.adarsh.condition.bean;

import com.espark.adarsh.condition.model.ServiceOne;
import com.espark.adarsh.condition.model.ServiceTwo;
import com.espark.adarsh.condition.model.ServiceThree;

@org.springframework.context.annotation.Configuration
public class ConditionalOnMissingBeanConfig {

    @org.springframework.context.annotation.Bean
    public ServiceOne serviceOne(){
        return new ServiceOne(); // will initialize as normal
    }

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean(name="serviceOne")
    public ServiceTwo serviceTwo(){
        return new ServiceTwo(); // it will not initialize as
                                 // beanA is present in the beanFactory.
    }

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean(name="serviceFour")
    public ServiceThree serviceThree(){
        return new ServiceThree(); // will get initialized as there is no
                                  // bean with name beanD in BeanFactory.
    }
   
}
