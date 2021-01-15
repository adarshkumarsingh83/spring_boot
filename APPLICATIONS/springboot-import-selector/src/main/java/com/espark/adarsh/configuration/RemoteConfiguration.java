package com.espark.adarsh.configuration;

import com.espark.adarsh.bean.ApplicationMessageBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoteConfiguration {

    @Value("${remote.configuration.msg}")
    String remoteConfigurationMessage;

    @Bean
    ApplicationMessageBean getApplicationMessageBean() {
        return new ApplicationMessageBean(remoteConfigurationMessage);
    }
}
