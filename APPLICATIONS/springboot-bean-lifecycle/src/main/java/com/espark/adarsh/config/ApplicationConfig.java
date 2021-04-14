package com.espark.adarsh.config;

import com.espark.adarsh.bean.LifeCycleBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public LifeCycleBean createLifeCycleBean(){
        return new LifeCycleBean();
    }
}
