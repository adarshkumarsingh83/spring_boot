package com.espark.adarsh.construct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.ProfileValueSource;

import java.io.IOException;
import java.util.Properties;


@Component
public class EsparkProfileValueSource implements ProfileValueSource {

    @Override
    public String get(String key) {
        Resource[] resources = new ClassPathResource[ ]
                { new ClassPathResource( "application.properties" ) };
        Properties properties=new Properties();
        try {
            properties.load(resources[0].getInputStream());
            return properties.getProperty("spring.profiles.active");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
