package com.espark.adarsh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("spring.hazelcast")
public class AppConfigProp {

    String cacheName;

    String repCacheName;

    String instanceName;

    String managementUrl;

    String userName;

    String userPwd;

    String publicAddress;

}
