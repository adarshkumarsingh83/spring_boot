package com.espark.adarsh.entity.convertors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Map;

@Slf4j
@Converter
public class AttributeMapConverter implements AttributeConverter<Map, String> {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
           log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Map convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData.toString()
                    , new TypeReference<Map<String, String>>() {
                    });

        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}


