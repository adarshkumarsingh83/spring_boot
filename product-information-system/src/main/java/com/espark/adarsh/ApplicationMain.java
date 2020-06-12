package com.espark.adarsh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ApplicationMain {

	@Value("${base.url}")
	private String baseUrl;

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
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
				"Espark Innovation Labs Rest Api",
				"Espark Rest Swagger Apis UI.",
				"Espark 1.0",
				"http://esparkinnovationlabs.com",
				"adarsh.espark@innovationlabs.com",
				"Espark Innovation Lab License",
				"http://esparkinnovationlab/icense");
		return apiInfo;
	}
}
