package com.espark.adarsh.service;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DataInitService {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostConstruct
    public void init() {
        employeeRepository.save(new Employee(1L, "adarsh", "kumar", "it"));
        employeeRepository.save(new Employee(2L, "amit", "kumar", "finance"));
        employeeRepository.save(new Employee(3L, "radha", "singh", "it"));
    }
}
