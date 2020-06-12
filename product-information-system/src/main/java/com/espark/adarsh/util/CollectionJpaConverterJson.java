package com.espark.adarsh.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Collection;

@Slf4j
@Converter(autoApply = true)
public class CollectionJpaConverterJson implements AttributeConverter<Collection, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Collection meta) {
        try {
            return objectMapper.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            log.error("label=collection-converter convertToDatabaseColumn() exception={}", ex);
            return null;
        }
    }

    @Override
    public Collection convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Collection.class);
        } catch (IOException ex) {
            log.error("label=collection-converter convertToEntityAttribute() exception={}", ex);
            return null;
        }
    }

}