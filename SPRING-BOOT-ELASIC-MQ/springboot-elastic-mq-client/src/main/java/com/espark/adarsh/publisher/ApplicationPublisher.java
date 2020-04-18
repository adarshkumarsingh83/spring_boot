package com.espark.adarsh.publisher;

import com.espark.adarsh.bean.MessageBean;
import com.espark.adarsh.util.CompressionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.IOException;

@Slf4j
@Service
public class ApplicationPublisher {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    CompressionUtil compressionUtil;

    @Value("${epsark.queue.producer:WORK-LOCAL-PRODUCING}")
    String workLocalQueue;


    public String sendMessage(MessageBean messageBean) throws IOException {
        String message = objectMapper.writeValueAsString(messageBean);
        byte[] compressedData = compressionUtil.compress(message);
        jmsTemplate.send(workLocalQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message jmsMessage = session.createObjectMessage(compressedData);
                jmsMessage.setStringProperty("traceId", System.nanoTime() + "");
                return jmsMessage;
            }
        });
        return "MESSAGE SEND SUCCESSFUL";
    }

}
