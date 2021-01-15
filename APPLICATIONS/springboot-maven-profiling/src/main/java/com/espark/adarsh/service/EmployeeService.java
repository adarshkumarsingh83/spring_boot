package com.espark.adarsh.service;


import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.repository.EmployeeRepository;
import com.espark.adarsh.service.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }


    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    public Employee getEmployeeById(Long empId) {
        return employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", empId));
    }


    public Employee updateEmployee(Long empId, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", empId));
        employee.setName(employeeDetails.getName());
        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }


    public Employee deleteEmployee(Long empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", empId));
        employeeRepository.delete(employee);
        return employee;
    }
}
