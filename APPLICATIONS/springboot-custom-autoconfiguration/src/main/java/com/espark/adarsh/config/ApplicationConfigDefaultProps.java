package com.espark.adarsh.config;

import com.espark.adarsh.service.EsparkApplicationService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(EsparkProcessConfig.class)
public class ApplicationConfigDefaultProps {

    @Bean
    @ConditionalOnMissingBean
    public EsparkApplicationService esparkApplicationService(){
        return new EsparkApplicationService(new EsparkServiceMessage("Without Service Activated"));
    }
}
