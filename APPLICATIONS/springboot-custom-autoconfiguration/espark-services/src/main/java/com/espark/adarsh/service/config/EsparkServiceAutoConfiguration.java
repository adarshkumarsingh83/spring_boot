package com.espark.adarsh.service.config;

import com.espark.adarsh.service.EsparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({EsparkService.class})
@EnableConfigurationProperties({EsparkProperties.class})
public class EsparkServiceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean({EsparkService.class})
    public EsparkService esparkService(@Autowired EsparkProperties esparkProperties) {
        return new EsparkService(esparkProperties);
    }

}
