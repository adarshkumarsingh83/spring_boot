package com.espark.adarsh.processor;

import com.espark.adarsh.bean.MessageBean;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor {

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public Object transform(MessageBean messageBean) {
        messageBean.setMessage(messageBean.getMessage().toUpperCase() + "_PROCESSED");
        return messageBean;
    }
}
