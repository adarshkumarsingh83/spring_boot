package com.espark.adarsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    @Autowired
    MessageChannel messageChannel;

    public String publishMessage(String messageData) {
        Message<String> message = MessageBuilder.withPayload(messageData+" "+ LocalDateTime.now())
                .build();
        return messageChannel.send(message) ? "message published " + LocalDateTime.now()
                : "message not published " + LocalDateTime.now();
    }
}
