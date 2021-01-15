package com.espark.adarsh.util.configuration;

import com.espark.adarsh.util.processor.AnnotationProcessor;
import com.espark.adarsh.util.processor.AnnotationProcessorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;


@Slf4j
@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@PropertySource("classpath:application.properties")
@ConditionalOnProperty(name = "espark.enable", havingValue = "true")
public class ApplicationConfiguration {

    @Autowired
    Environment environment;

    @Bean
    @ConditionalOnMissingBean
    public EsparkEnabler instantiateApplicationMessageBean() {
        log.info("label=configuration ApplicationMessageBean");
        return new EsparkEnabler();
    }

    @Bean
    @ConditionalOnBean(value = EsparkEnabler.class)
    AnnotationProcessor createAnnotationProcessorImpl(){
        return new AnnotationProcessorImpl();
    }
}
