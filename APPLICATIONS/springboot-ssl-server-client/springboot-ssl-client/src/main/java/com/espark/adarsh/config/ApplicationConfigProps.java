package com.espark.adarsh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;

@Data
@Component
@ConfigurationProperties("server")
public class ApplicationConfigProps {

    String keyStore;
    String keyStorePassword;
    String serviceHost;
    String protocall;
    Map<String, String> serviceUrl;
}
