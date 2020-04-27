package com.espark.adarsh.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Slf4j
@Configuration
public class SwaggerConfiguration {


    @Autowired
    SwaggerLocations swaggerLocations;

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();
            if (swaggerLocations.getDirectories() != null && !swaggerLocations.getDirectories().isEmpty()) {
                swaggerLocations.getDirectories()
                        .forEach(directories -> resources.addAll(loadResource(directories)));
            }
            resources.forEach(resource ->
                    log.info("label=swagger-configuration {}", resource.getUrl()));
            return resources;
        };
    }

    private List<SwaggerResource> loadResource(SwaggerLocations.Directories directories) {
        List<SwaggerResource> swaggerResourceList = directories.locations.stream().map(location -> {
            SwaggerResource wsResource = new SwaggerResource();
            wsResource.setName(location);
            wsResource.setSwaggerVersion("2.0");
            wsResource.setLocation("/" + directories.getDirectory() + "/" + location + "/" + directories.getFileName());
            return wsResource;
        }).collect(Collectors.toList());
        return swaggerResourceList;
    }

    @Bean
    public Docket swagger() {
        return new Docket(SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
