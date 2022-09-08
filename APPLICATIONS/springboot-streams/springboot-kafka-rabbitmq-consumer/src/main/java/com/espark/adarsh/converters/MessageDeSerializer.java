package com.espark.adarsh.converters;

import com.espark.adarsh.bean.MessageBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageDeSerializer implements Deserializer<MessageBean> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public MessageBean deserialize(String topic, byte[] data) {
        try {
            System.out.println("MessageDeSerializer");
            return objectMapper.readValue(new String(data), MessageBean.class);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }
}