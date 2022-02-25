package com.espark.adarsh.config;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

@Slf4j
@Configuration
public class ApplicationConfig {

    @Bean
    MqttPahoClientFactory clientFactory(@Value("${hivemq.uri}") String hostUrl,
                                        @Value("${hivemq.username}") String username,
                                        @Value("${hivemq.password}") String password) {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{hostUrl});
        options.setUserName(username);
        options.setConnectionTimeout(1000);
        options.setPassword(password.toCharArray());
        factory.setConnectionOptions(options);
        return factory;
    }
}
