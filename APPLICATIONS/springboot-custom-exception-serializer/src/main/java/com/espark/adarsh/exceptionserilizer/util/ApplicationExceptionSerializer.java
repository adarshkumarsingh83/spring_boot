package com.espark.adarsh.exceptionserilizer.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class ApplicationExceptionSerializer {

    public static class ExceptionSerializer extends JsonSerializer<Exception> {
        @Override
        public void serialize(Exception e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            System.out.println("exception serializer executed ");
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("class", e.getClass().getName());
            jsonGenerator.writeStringField("message", e.getMessage());
            jsonGenerator.writeStringField("localMessage", e.getLocalizedMessage());
            jsonGenerator.writeEndObject();
        }
    }


    public static class ExceptionDeserializer extends JsonDeserializer<Exception> {
        @Override
        public Exception deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException, JsonProcessingException {
            System.out.println("exception de-serializer executed ");
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
            String message = node.get("message").asText();
            String className = node.get("class").asText();
            return new Exception(message);
        }
    }

}
