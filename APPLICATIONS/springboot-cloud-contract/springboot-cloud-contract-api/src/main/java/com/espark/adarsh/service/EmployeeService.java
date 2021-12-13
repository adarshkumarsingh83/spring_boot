package com.espark.adarsh.service;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Collection<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }
}
