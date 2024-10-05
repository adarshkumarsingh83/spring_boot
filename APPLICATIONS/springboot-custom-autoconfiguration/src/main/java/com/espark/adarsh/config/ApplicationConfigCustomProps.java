package com.espark.adarsh.config;

import com.espark.adarsh.service.EsparkApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(EsparkProcessConfig.class)
public class ApplicationConfigCustomProps {

    @Autowired
    EsparkProcessConfig esparkProcessConfig;

    @Bean
    @ConditionalOnMissingBean
    public EsparkApplicationService esparkApplicationService(){
        return new EsparkApplicationService(this.esparkProcessConfig.getService());
    }
}
