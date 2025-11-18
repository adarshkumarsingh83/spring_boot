package com.espark.adarsh.config;

import com.espark.adarsh.annotation.EsparkQualifier1;
import com.espark.adarsh.annotation.EsparkQualifier2;
import com.espark.adarsh.service.EsparkService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {



    @Bean
    @EsparkQualifier1
    EsparkService esparkService1(){
        return new EsparkService() {
            @Override
            public String getServiceName() {
                return  "Espark Service1 Supplier Implementation";
            }
        };
    }

    @EsparkQualifier2
    @Bean
    EsparkService esparkService2() {
        return new EsparkService() {
            @Override
            public String getServiceName() {
                return "Espark Service2 Supplier Implementation";
            }
        };
    }
}
