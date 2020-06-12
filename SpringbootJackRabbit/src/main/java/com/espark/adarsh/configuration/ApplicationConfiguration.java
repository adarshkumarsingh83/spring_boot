package com.espark.adarsh.configuration;


import org.apache.jackrabbit.commons.JcrUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.jcr.Repository;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Repository getRepository()throws Exception{
         return JcrUtils.getRepository();
    }

}
