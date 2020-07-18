package com.espark.adarsh.service;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public Employee saveEmployees(Employee employee) {
        log.info("label=EmployeeService saveEmployees()");
        Employee employees = this.employeeRepository.save(employee);
        return employees;
    }

    public List<Employee> getEmployees() {
        log.info("label=EmployeeService getEmployees()");
        List<Employee> employees = new LinkedList<>();
        this.employeeRepository.findAll().forEach(employee -> employees.add(employee));
        return employees;
    }

    public Employee getEmployee(Integer id) {
        log.info("label=EmployeeService getEmployee()");
        Optional<Employee> employeeOptional = this.employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        } else {
            return null;
        }
    }

    public Employee deleteEmployee(Integer id) {
        log.info("label=EmployeeService deleteEmployee()");
        Optional<Employee> employeeOptional = this.employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            this.employeeRepository.delete(employee);
            return employee;
        } else {
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }
    }
}
