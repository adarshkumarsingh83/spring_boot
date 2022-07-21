package com.espark.adarsh.util;

import com.espark.adarsh.bean.Message;
import com.espark.adarsh.config.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class KafkaMessageProducer {

    @Autowired
    private StreamBridge streamBridge;


    @Autowired
    TimeUtil timeUtil;


    @Scheduled(cron = "*/2 * * * * *")
    public void sendMessage() {
        streamBridge.send(ApplicationConfig.OUT, new Message("PUBLISHER:-> Welcome to the espark " + timeUtil.getTime()));
    }
}
