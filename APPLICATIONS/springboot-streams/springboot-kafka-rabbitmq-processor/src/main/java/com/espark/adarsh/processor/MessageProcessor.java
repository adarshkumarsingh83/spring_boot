package com.espark.adarsh.processor;

import com.espark.adarsh.bean.MessageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageProcessor {

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public Object transform(MessageBean messageBean) {
        log.info("Message Bean Before Processing {}", messageBean);
        String temp = messageBean.getMessage().toUpperCase() + "_PROCESSED";
        messageBean.setMessage(temp);
        log.info("Message Bean After Processing {}", messageBean);
        return messageBean;
    }
}
