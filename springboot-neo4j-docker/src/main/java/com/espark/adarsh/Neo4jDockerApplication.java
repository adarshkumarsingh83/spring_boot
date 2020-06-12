package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("com.espark.adarsh.repository")
public class Neo4jDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neo4jDockerApplication.class, args);
	}

}
