package com.espark.adarsh.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;

@Slf4j
@Configuration
public class OutBoundConfiguration {

    @Bean
    MessageChannel messageChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    MqttPahoMessageHandler pahoMessageHandler(@Value("${hivemq.topic}") String topic,
                                              @Value("${hivemq.clientId}") String clientId,
                                              MqttPahoClientFactory clientFactory) {
        MqttPahoMessageHandler mqttPahoMessageHandler = new MqttPahoMessageHandler(clientId, clientFactory);
        mqttPahoMessageHandler.setDefaultTopic(topic);
        return mqttPahoMessageHandler;
    }

    @Bean
    IntegrationFlow integrationFlow(MessageChannel messageChannel,
                                 MqttPahoMessageHandler pahoMessageHandler) {
        return IntegrationFlows.from(messageChannel)
                .handle(pahoMessageHandler)
                .get();
    }
}
