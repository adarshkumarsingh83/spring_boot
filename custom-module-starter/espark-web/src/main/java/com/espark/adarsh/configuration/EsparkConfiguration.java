package com.espark.adarsh.configuration;

import com.espark.adarsh.service.EsparkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class EsparkConfiguration {

    @Bean
    @ConditionalOnExpression("'${espark.service.enable}' != 'true'")
    @ConditionalOnMissingBean(EsparkService.class)
    public EsparkService instantiateEsparkService() {
        log.info("label=EsparkConfiguration InMemory Service");
        return new EsparkService() {
            @Override
            public String doOperation(String name) {
                return "In Memory EsparkService " + name;
            }
        };
    }

}
