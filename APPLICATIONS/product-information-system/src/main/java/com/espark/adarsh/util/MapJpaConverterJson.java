package com.espark.adarsh.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Converter(autoApply = true)
public class MapJpaConverterJson implements AttributeConverter<Map, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map meta) {
        try {
            return objectMapper.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            log.error("label=map-converter convertToDatabaseColumn() exception={}", ex);
            return null;
        }
    }

    @Override
    public Map convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Map.class);
        } catch (IOException ex) {
            log.error("label=map-converter convertToEntityAttribute() exception={}", ex);
            return null;
        }
    }

}