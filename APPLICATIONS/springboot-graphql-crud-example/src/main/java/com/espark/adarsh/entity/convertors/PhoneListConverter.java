package com.espark.adarsh.entity.convertors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Slf4j
@Converter
public class PhoneListConverter implements AttributeConverter<List<String>, String> {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List phone) {
        try {
            return objectMapper.writeValueAsString(phone
            );
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData
                    , new TypeReference<List<String>>() {
                    });

        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}