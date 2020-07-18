package com.espark.adarsh.util;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostConstruct
    public void init() {
        Employee adarsh = new Employee();
        adarsh.setId(100);
        adarsh.setName("adarsh kumar");
        employeeRepository.save(adarsh);


        Employee radha = new Employee();
        radha.setId(200);
        radha.setName("radha singh");
        employeeRepository.save(radha);
    }
}
