package com.espark.adarsh.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ApplicationConfiguration {

    @Value("${base.url}")
    private String baseUrl;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).pathMapping(baseUrl)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.espark.adarsh"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "IRIS DATA INTI Rest Api",
                "IRIS Rest Swagger Apis UI.",
                "IRIS 1.0",
                "http://jcpenney.com",
                "admin.jcpenney.com",
                "JCPENNEY",
                "http://jcpenney/icense");
        return apiInfo;
    }
}
