package com.espark.adarsh.configuration;

import com.espark.adarsh.service.EsparkService;
import com.espark.adarsh.service.EsparkServiceDefaultImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ApplicationConfiguration {

    @Bean("esparkServiceDefaultImpl")
    @ConditionalOnExpression("'${espark.service.enable}' != 'true'")
    public EsparkService instantiateDefaultService() {
        log.info("label=configuration EsparkServiceDefaultImpl");
        return new EsparkServiceDefaultImpl();
    }
}
