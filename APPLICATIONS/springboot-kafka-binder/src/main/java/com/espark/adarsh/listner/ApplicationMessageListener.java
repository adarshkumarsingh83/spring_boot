package com.espark.adarsh.listner;

import com.espark.adarsh.bean.ApplicationMessage;
import com.espark.adarsh.config.ApplicationKafkaChannelConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationMessageListener {

    @StreamListener(ApplicationKafkaChannelConfig.INPUT_CHANNEL)
    public void process(Message<ApplicationMessage> message) {
        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        if (acknowledgment != null) {
            log.info("Acknowledgment provided");
            acknowledgment.acknowledge();
        }
        log.info(message.getPayload().toString());
    }
}
