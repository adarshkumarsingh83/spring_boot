package com.espark.adarsh.coverters;

import com.espark.adarsh.bean.ApplicationMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageDeSerializer implements Deserializer<ApplicationMessage> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ApplicationMessage deserialize(String topic, byte[] data) {
        try {
            System.out.println("MessageDeSerializer");
            return objectMapper.readValue(new String(data), ApplicationMessage.class);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }
}
