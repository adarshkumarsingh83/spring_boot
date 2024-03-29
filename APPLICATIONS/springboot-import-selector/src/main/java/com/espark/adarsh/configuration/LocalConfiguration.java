package com.espark.adarsh.configuration;

import com.espark.adarsh.bean.ApplicationMessageBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LocalConfiguration {

    @Value("${local.configuration.msg}")
    String localConfigurationMessage;

    @Bean
    ApplicationMessageBean getApplicationMessageBean() {
        return new ApplicationMessageBean(localConfigurationMessage);
    }
}
