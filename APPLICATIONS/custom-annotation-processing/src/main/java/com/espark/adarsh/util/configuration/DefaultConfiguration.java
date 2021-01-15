package com.espark.adarsh.util.configuration;

import com.espark.adarsh.util.processor.AnnotationProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DefaultConfiguration {

    @Bean
    @ConditionalOnExpression("'${espark.enable}' != 'true'")
    public AnnotationProcessor instantiateDefaultProcessor() {
        log.info("label=configuration EsparkServiceDefaultImpl");
        return new AnnotationProcessor() {
        };
    }
}
