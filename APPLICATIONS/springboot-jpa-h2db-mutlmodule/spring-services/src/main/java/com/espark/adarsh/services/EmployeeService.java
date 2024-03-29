package com.espark.adarsh.services;

import com.espark.adarsh.persistence.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    Employee getEmployee(Long id);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);
}
