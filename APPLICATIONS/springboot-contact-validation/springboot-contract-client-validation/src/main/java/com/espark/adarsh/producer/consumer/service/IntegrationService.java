package com.espark.adarsh.producer.consumer.service;

import com.espark.adarsh.producer.consumer.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IntegrationService {

    @Autowired
    RestTemplate restTemplate;

    public Employee getEmployee(final int id) {
        final ResponseEntity<Employee> result = restTemplate.exchange("http://localhost:9090/employee/" + id,
                HttpMethod.GET, null, Employee.class);
        return result.getBody();
    }
}
