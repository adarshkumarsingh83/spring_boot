package com.espark.adarsh.config;

import com.espark.adarsh.annotation.EsparkQualifier1;
import com.espark.adarsh.annotation.EsparkQualifier2;
import com.espark.adarsh.util.ServiceUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {

    @Bean
    @EsparkQualifier1
    ServiceUtil service1Util(){
        return new ServiceUtil() {
            @Override
            public String getServiceName() {
                return "Espark Service1 Util Supplier Implementation";
            }
        };
    }


    @Bean
    @EsparkQualifier2
    ServiceUtil service2Util(){
        return new ServiceUtil() {
            @Override
            public String getServiceName() {
                return "Espark Service2 Util Supplier Implementation";
            }
        };
    }

}
