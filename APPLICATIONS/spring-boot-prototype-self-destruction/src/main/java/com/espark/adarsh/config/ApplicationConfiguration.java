package com.espark.adarsh.config;

import com.espark.adarsh.bean.PrototypeSampleBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {

    @Bean("prototypeSampleBean")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PrototypeSampleBean getPrototypeSampleBean() {
        return new PrototypeSampleBean();
    }
}
