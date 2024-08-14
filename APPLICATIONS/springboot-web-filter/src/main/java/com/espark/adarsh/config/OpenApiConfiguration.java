package com.espark.adarsh.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .info(new Info()
                        .title("Espark Api ")
                        .description("swagger for espark api")
                        .version("1.0")
                        .contact(new Contact().name("adarsh kumar ")
                                .email("adarsh.kumar@espark.com")
                                .url("adarsh.kumar.espark.com"))
                );
    }
}
