package com.espark.adarsh.condition.bean;

import com.espark.adarsh.condition.model.ServiceOne;
import com.espark.adarsh.condition.model.ServiceTwo;
import com.espark.adarsh.condition.model.ServiceThree;
import com.espark.adarsh.condition.model.PrintService;
import com.espark.adarsh.condition.model.BlackAndWhitePrinterService;
import com.espark.adarsh.condition.model.ColorPrinterService;


@org.springframework.context.annotation.Configuration
public class ConditionalOnBeanConfig {

    @org.springframework.context.annotation.Bean
    public ServiceOne serviceOne(){
        // will initialize as normal
        return new ServiceOne();
    }

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnBean(name="serviceOne")
    public ServiceTwo serviceTwo(){
        // it will initialize as serviceOne is present in the beanFactory.
        return new ServiceTwo();
    }

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnBean
    public ServiceThree serviceThree(){
        // will not get initialized as there is no bean with return type serviceThree in BeanFactory.
        return new ServiceThree();
    }

    @org.springframework.context.annotation.Bean
    public PrintService blackAndWhitePrinterService(){
        return new BlackAndWhitePrinterService();
    }

    @org.springframework.context.annotation.Bean
    @org.springframework.boot.autoconfigure.condition.ConditionalOnBean
    public PrintService colorPrinterService(){
        return new ColorPrinterService();
    }
}
