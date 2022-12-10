package com.espark.adarsh;

import com.espark.adarsh.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class SpringbootBasicCrudExampleApplicationTests {


    @Autowired
    RestTemplateBuilder restTemplateBuilder;


    @Test
    void contextLoads() {
    }

    @Test
    void testGetAllEmployeeApi() throws IOException, InterruptedException {
        try (GenericContainer container = new GenericContainer<>("adarshkumarsingh83/springboot-test-container-example")
                .withExposedPorts(8080)) {
            container.start();
            String baseUrl = "http://" + container.getHost() + ":" + container.getFirstMappedPort();
            RestTemplate restTemplate = restTemplateBuilder.rootUri(baseUrl).build();
            ResponseEntity<List<Employee>> response = restTemplate.exchange("/employees", HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
            }, new Object[]{});
            log.info("Response from Test Container  {}", response);
        }
    }

    @Test
    void testGetEmployeeApi() throws IOException, InterruptedException {
        try (GenericContainer container = new GenericContainer<>("adarshkumarsingh83/springboot-test-container-example")
                .withExposedPorts(8080)) {
            container.start();
            String baseUrl = "http://" + container.getHost() + ":" + container.getFirstMappedPort();
            RestTemplate restTemplate = restTemplateBuilder.rootUri(baseUrl).build();
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", "1");
            ResponseEntity<Employee> response = restTemplate.getForEntity("/employee/{id}", Employee.class, params);
            log.info("Response from Test Container  {}", response);
        }
    }

    @Test
    void testPostEmployeeApi() throws IOException, InterruptedException {
        try (GenericContainer container = new GenericContainer<>("adarshkumarsingh83/springboot-test-container-example")
                .withExposedPorts(8080)) {
            container.start();
            String baseUrl = "http://" + container.getHost() + ":" + container.getFirstMappedPort();
            RestTemplate restTemplate = restTemplateBuilder.rootUri(baseUrl).build();
            Employee newEmployee = new Employee(100L,"admin", "admin", "bench");
            ResponseEntity<Employee> response = restTemplate.postForEntity("/employee", newEmployee, Employee.class);
            log.info("Response from Test Container  {}", response);
        }
    }

    @Test
    void testPutEmployeeApi() throws IOException, InterruptedException {
        try (GenericContainer container = new GenericContainer<>("adarshkumarsingh83/springboot-test-container-example")
                .withExposedPorts(8080)) {
            container.start();
            String baseUrl = "http://" + container.getHost() + ":" + container.getFirstMappedPort();
            RestTemplate restTemplate = restTemplateBuilder.rootUri(baseUrl).build();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", "1");
            Employee employee = new Employee(1L, "ADARSH", "KUMAR", "FOUNDER");
            HttpEntity<Employee> httpEntity = new HttpEntity<Employee>(employee, httpHeaders);
            ResponseEntity<Employee> response = restTemplate.exchange("/employee/{id}", HttpMethod.PUT, httpEntity, Employee.class, params);
            log.info("Response from Test Container  {}", response);
        }
    }


    @Test
    void testPatchEmployeeApi() throws IOException, InterruptedException {
        try (GenericContainer container = new GenericContainer<>("adarshkumarsingh83/springboot-test-container-example")
                .withExposedPorts(8080)) {
            container.start();
            String baseUrl = "http://" + container.getHost() + ":" + container.getFirstMappedPort();
            RestTemplate restTemplate = restTemplateBuilder.requestFactory(() -> new HttpComponentsClientHttpRequestFactory()).rootUri(baseUrl).build();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", "1");
            Map<String, String> employee = new HashMap<String, String>() {
                {
                    put("career", "NEW-FOUNDER");
                }
            };
            HttpEntity<Map> httpEntity = new HttpEntity<Map>(employee, httpHeaders);
            ResponseEntity<Employee> response = restTemplate.exchange("/employee/{id}", HttpMethod.PATCH, httpEntity, Employee.class, params);
            log.info("Response from Test Container  {}", response);
        }
    }

    @Test
    void testDeleteEmployeeApi() throws IOException, InterruptedException {
        try (GenericContainer container = new GenericContainer<>("adarshkumarsingh83/springboot-test-container-example")
                .withExposedPorts(8080)) {
            container.start();
            String baseUrl = "http://" + container.getHost() + ":" + container.getFirstMappedPort();
            RestTemplate restTemplate = restTemplateBuilder.rootUri(baseUrl).build();

            Map<String, String> params = new HashMap<String, String>();
            params.put("id", "1");

            ResponseEntity<Employee> response = restTemplate.exchange("/employee/{id}", HttpMethod.DELETE, null, Employee.class, params);
            log.info("Response from Test Container  {}", response);
        }
    }

}
