package com.espark.adarsh.configuration;

import com.espark.adarsh.bean.ApplicationMessageBean;
import com.espark.adarsh.configuration.condition.LocalCondition;
import com.espark.adarsh.configuration.condition.RemoteCondition;
import com.espark.adarsh.service.EsparkService;
import com.espark.adarsh.service.EsparkServiceDefaultImpl;
import com.espark.adarsh.service.EsparkServiceLocalImpl;
import com.espark.adarsh.service.EsparkServiceRemoteImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Slf4j
@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@PropertySource("classpath:espark.properties")
@ConditionalOnProperty(name = "espark.service.enable", havingValue = "true")
public class EsparkServiceAutoConfiguration {


    @Autowired
    private Environment environment;

    @Bean
    @ConditionalOnResource(resources = "classpath:espark.properties")
    @ConditionalOnMissingBean
    public ApplicationMessageBean instantiateApplicationMessageBean() {
        log.info("label=configuration ApplicationMessageBean");
        return new ApplicationMessageBean(environment.getProperty("espark.application.msg"));
    }

    @Bean("esparkServiceDefaultImpl")
    @ConditionalOnMissingBean(EsparkService.class)
    public EsparkService instantiateDefaultService() {
        log.info("label=configuration EsparkServiceDefaultImpl");
        return new EsparkServiceDefaultImpl();
    }

    @Bean("esparkServiceLocalImpl")
    @ConditionalOnMissingBean
    @ConditionalOnBean(ApplicationMessageBean.class)
    @ConditionalOnProperty(name = "espark.service.type", havingValue = "local")
    public EsparkService instantiateLocalService() {
        log.info("label=configuration EsparkServiceLocalImpl");
        return new EsparkServiceLocalImpl();
    }

    @Bean("esparkServiceRemoteImpl")
    @ConditionalOnMissingBean
    @ConditionalOnBean(ApplicationMessageBean.class)
    @ConditionalOnProperty(name = "espark.service.type", havingValue = "remote")
    public EsparkService instantiateRemoteService() {
        log.info("label=configuration EsparkServiceRemoteImpl");
        return new EsparkServiceRemoteImpl();
    }

    @Bean
    @ConditionalOnResource(resources = "classpath:espark.properties")
    @Conditional(LocalCondition.class)
    public Properties localProperties() {
        Properties localProperties = new Properties();
        localProperties.setProperty("local.espark.msg", environment.getProperty("local.espark.msg"));
        return localProperties;
    }

    @Bean
    @ConditionalOnResource(resources = "classpath:espark.properties")
    @Conditional(RemoteCondition.class)
    public Properties remoteProperties() {
        Properties remoteProperties = new Properties();
        remoteProperties.setProperty("remote.espark.msg", environment.getProperty("remote.espark.msg"));
        return remoteProperties;
    }

}
