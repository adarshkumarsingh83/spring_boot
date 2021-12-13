package com.espark.adarsh.service;

import com.espark.adarsh.bean.Employee;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

public class EmployeeApiServiceClient {

    RestTemplate restTemplate;

    public EmployeeApiServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Collection<Employee> getAllEmployees() throws Exception {
        ParameterizedTypeReference<Collection<Employee>> parameterizedTypeReference = new ParameterizedTypeReference<Collection<Employee>>() {
        };
        ResponseEntity<Collection<Employee>> responseEntity = this.restTemplate.exchange("http://localhost:8081/employees", HttpMethod.GET, null, parameterizedTypeReference);
        return responseEntity.getBody();
    }
}
