package com.espark.adarsh.web;

import com.espark.adarsh.bean.ApplicationMessage;
import com.espark.adarsh.config.ApplicationKafkaChannelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationMessagePublisherController {

    @Autowired
    ApplicationKafkaChannelConfig applicationKafkaChannelConfig;

    @PostMapping("/api/message")
    public Map<String, String> postMessage(@RequestBody ApplicationMessage applicationMessage) {

        if (applicationKafkaChannelConfig.applicationPublisherStream().send(MessageBuilder.withPayload(applicationMessage)
                .setHeader("content-type", "application/json")
                .setErrorChannelName("espark-error-channel")
                .build())) {
            return new HashMap<String,String>() {
                {
                    put("message", "message posted on kafka is successful ");
                }
            };
        } else {
            return new HashMap<String,String>() {
                {
                    put("message", "message posted on kafka is failed");
                }
            };
        }
    }
}
