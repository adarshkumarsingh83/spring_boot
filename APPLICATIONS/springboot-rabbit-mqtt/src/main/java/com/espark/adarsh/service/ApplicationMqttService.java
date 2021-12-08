package com.espark.adarsh.service;

import com.espark.adarsh.beans.MqttProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;


/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */

@Slf4j
@Service
public class ApplicationMqttService {

    @Autowired
    MqttClient mqttClient;

    @Autowired
    MqttProperties properties;

    public void transformData(String mqttTopic, String message) throws Exception {
        log.debug("Mqtt transformData mqttTopic = {} with message = {} ", mqttTopic, message);
        Thread.currentThread().sleep(1000);
        message = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        this.publish(properties.getTopicPublish(), message, 1, false);
    }


    public void publish(final String topic, final String payload, int qos, boolean retained)
            throws MqttException {
        log.info("<<<= Publishing to Mqtt  Topic = {}  payload = {}", topic, payload);
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(payload.getBytes());
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);
        mqttClient.publish(topic, mqttMessage);
    }
}
