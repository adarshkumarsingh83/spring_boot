package com.espark.adarsh.util;

public interface ApplicationUtil {

    public static final String HOME="department";

    public default String getJsonString(Object object) throws java.io.IOException {
        final com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        mapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }

    public default Object jsonToObject(String jsonString) throws java.io.IOException {
        final com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        mapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
        return mapper.readValue(jsonString, new com.fasterxml.jackson.core.type.TypeReference<Object>() { });
    }

}
