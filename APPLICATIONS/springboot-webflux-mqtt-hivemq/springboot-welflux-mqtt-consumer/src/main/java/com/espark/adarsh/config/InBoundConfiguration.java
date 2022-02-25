package com.espark.adarsh.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.MessageHeaders;

@Slf4j
@Configuration
public class InBoundConfiguration {

    @Bean
    MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter(@Value("${hivemq.clientId}") String clientId,
                                                                    @Value("${hivemq.topic}") String topic,
                                                                    MqttPahoClientFactory clientFactory) {
        return new MqttPahoMessageDrivenChannelAdapter(clientId, clientFactory, topic);
    }

    @Bean
    IntegrationFlow inBoundFlow(MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter
            , GenericHandler genericHandle) {
        return IntegrationFlows.from(messageDrivenChannelAdapter)
                .handle(genericHandle)
                .get();
    }


    @Bean
    GenericHandler genericHandler() {
        final GenericHandler genericHandler = (payload, headers) -> {
            log.info("Message Payload {}", payload);
            headers.forEach((k, v) -> log.info(k + " " + v));
            return null;
        };
        return genericHandler;
    }
}
