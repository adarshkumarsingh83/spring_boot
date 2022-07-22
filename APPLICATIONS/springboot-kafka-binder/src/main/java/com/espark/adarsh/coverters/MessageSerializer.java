package com.espark.adarsh.coverters;

import com.espark.adarsh.bean.ApplicationMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

@Component
public class MessageSerializer implements Serializer<ApplicationMessage> {

     private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, ApplicationMessage applicationMessage) {
        try {
            System.out.println("MessageSerializer");
            return objectMapper.writeValueAsBytes(applicationMessage);
        } catch (JsonProcessingException e) {
            throw new SerializationException(e);
        }
    }
}