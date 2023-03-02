package com.espark.adarsh.config;

import com.espark.adarsh.entities.Employee;
import org.springframework.cloud.stream.schema.client.ConfluentSchemaRegistryClient;
import org.springframework.cloud.stream.schema.client.SchemaRegistryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ApplicationConfig {

    @Value("${spring.cloud.stream.kafka.binder.producer-properties.schema.registry.url}")
    private String endPoint;

    @Bean
    public SchemaRegistryClient schemaRegistryClient() {
        ConfluentSchemaRegistryClient client = new ConfluentSchemaRegistryClient();
        client.setEndpoint(endPoint);
        return client;
    }
}
