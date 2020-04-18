package com.espark.adarsh.consumer;

import com.espark.adarsh.bean.MessageBean;
import com.espark.adarsh.util.CompressionUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
public class ApplicationConsumer {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CompressionUtil compressionUtil;

    @JmsListener(destination = "${epsark.queue.consumer:WORK-LOCAL-CONSUMING}")
    void onMessageReceived(byte[] message, @Headers Map<String, Object> headers) throws IOException {

        if (message != null && message.length != 0) {
            String json = this.compressionUtil.decompress(message);
            MessageBean messageBean = this.objectMapper.readValue(json, new TypeReference<MessageBean>() {
            });
            log.info("label=onMessageReceived() ", messageBean.getData());
        }
    }

}
