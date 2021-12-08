package com.espark.adarsh.config;

import com.espark.adarsh.beans.MqttProperties;
import com.espark.adarsh.handler.MQTTMessageHandler;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */

@EnableScheduling
@Configuration
@EnableConfigurationProperties({
        MqttProperties.class
})
public class MQTTConfiguration {

    @Autowired
    MqttProperties properties;

    @Bean
    public MqttConnectOptions mqttConnectOptions(MqttProperties mqttProperties) {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{mqttProperties.getHost()});
        options.setCleanSession(mqttProperties.getCleanSession());
        options.setUserName(mqttProperties.getUsername());
        options.setPassword(mqttProperties.getPassword().toCharArray());
        options.setConnectionTimeout(mqttProperties.getConnectionTimeout());
        options.setKeepAliveInterval(mqttProperties.getKeepAliveInterval());
        options.setAutomaticReconnect(mqttProperties.getAutomaticReconnect());
        options.setWill(properties.getErrorTopic(), "ServerOffline".getBytes(), 2, true);
        return options;
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory(MqttConnectOptions options) {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(options);
        return factory;
    }


    @Bean
    public MqttClient mqttClient(MqttProperties mqttProperties, MqttConnectOptions options) throws MqttException {
        MqttClient mqttClient = new MqttClient(mqttProperties.getHost(), mqttProperties.getClientId() + System.currentTimeMillis());
        mqttClient.connect(options);
        return mqttClient;
    }

    @Bean
    public IntegrationFlow mqttInbound(MqttProperties settings,
                                       MqttPahoClientFactory mqttClientFactory,
                                       MQTTMessageHandler mqttMessageHandler) {
        return IntegrationFlows.from(
                new MqttPahoMessageDrivenChannelAdapter(settings.getClientId() + System.currentTimeMillis(), mqttClientFactory, settings.getTopicSub()))
                .handle(mqttMessageHandler)
                .get();
    }

}
