package com.espark.adarsh.service;

import com.espark.adarsh.bean.MessageBean;
import com.espark.adarsh.config.MessageStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;


@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageStreams messageStreams;

    @Override
    public void sendMessage(final MessageBean messageBean) {
        log.info("Sending messageBean {}", messageBean);
        MessageChannel messageChannel = messageStreams.outboundMessage();
        messageChannel.send(MessageBuilder
                .withPayload(messageBean)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE)
                .build());
    }

}
