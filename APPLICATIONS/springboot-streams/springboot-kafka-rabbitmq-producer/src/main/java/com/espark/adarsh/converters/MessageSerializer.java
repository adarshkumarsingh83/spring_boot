package com.espark.adarsh.converters;

import com.espark.adarsh.bean.MessageBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

@Component
public class MessageSerializer implements Serializer<MessageBean> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, MessageBean applicationMessage) {
        try {
            System.out.println("MessageSerializer");
            return objectMapper.writeValueAsBytes(applicationMessage);
        } catch (JsonProcessingException e) {
            throw new SerializationException(e);
        }
    }
}