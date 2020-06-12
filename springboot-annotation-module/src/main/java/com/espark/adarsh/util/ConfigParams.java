package com.espark.adarsh.util;

import com.espark.adarsh.annotation.EsparkMethod;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConfigParams {

    public static final String PACKAGE_NAME = "package.name";


    @EsparkMethod
    @Bean(name = "packageScan")
    @ConfigurationProperties( prefix = "espark.packages" )
    public List<String> ignoreFileNames(){
        return new ArrayList<String>();
    }


    @EsparkMethod
    @Bean(name = "classAnnotationToScan")
    @ConfigurationProperties( prefix = "espark.annotation.class" )
    public List<String> classAnnotationToScan(){
        return new ArrayList<String>();
    }


    @EsparkMethod
    @Bean(name = "methodAnnotationToScan")
    @ConfigurationProperties( prefix = "espark.annotation.method" )
    public List<String> methodAnnotationToScan(){
        return new ArrayList<String>();
    }

}
