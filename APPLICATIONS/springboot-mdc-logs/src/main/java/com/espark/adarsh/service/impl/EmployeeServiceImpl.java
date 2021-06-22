package com.espark.adarsh.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.espark.adarsh.controller.EmployeeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Override
    public List<Employee> getAllEmployee() {
        LOGGER.info("Inside getAllEmployee method of EmployeeServiceImpl");
        List<Employee> employees = new ArrayList<Employee>();
        Employee employee1 = new Employee(1, "Adarsh kumar", 1000);
        Employee employee2 = new Employee(2, "Radha Singh", 1000);
        Employee employee3 = new Employee(3, "Amit Kumar", 1000);
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        LOGGER.info("list of employees : {} ", employees);
        return employees;
    }

}
