package com.espark.adarsh.util;

import com.espark.adarsh.entity.Product.Price;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Slf4j
@Converter(autoApply = true)
public class PriceConverterJson implements AttributeConverter<Price, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Price price) {
        try {
            return objectMapper.writeValueAsString(price);
        } catch (JsonProcessingException ex) {
            log.error("label=price-converter convertToDatabaseColumn() exception={}", ex);
            return null;
        }
    }

    @Override
    public Price convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Price.class);
        } catch (IOException ex) {
            log.error("label=collection-converter convertToEntityAttribute() exception={}", ex);
            return null;
        }
    }

}