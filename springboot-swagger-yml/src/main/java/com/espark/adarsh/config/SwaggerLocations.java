package com.espark.adarsh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties("espark.swagger")
public class SwaggerLocations {

    List<Directories> directories;

    @Data
    static class Directories{
        String fileName;
        String directory;
        List<String> locations;
    }
}
