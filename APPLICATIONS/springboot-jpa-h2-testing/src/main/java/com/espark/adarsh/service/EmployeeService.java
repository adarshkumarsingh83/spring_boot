package com.espark.adarsh.service;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.respository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class EmployeeService {


    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee() {
        log.info("EmployeeService.getAllEmployee()");
        List<Employee> employeeList = new LinkedList<>();
        this.employeeRepository.findAll().forEach(employee -> employeeList.add(employee));
        return employeeList;
    }

    public Employee getEmployee(Long id) {
        log.info("EmployeeService.getEmployee(), {}", id);
        return this.employeeRepository.findById(id).get();
    }

    public Employee removeEmployee(Long id) {
        log.info("EmployeeService.removeEmployee(), {}", id);
        Employee employee = this.employeeRepository.findById(id).get();
        this.employeeRepository.deleteById(id);
        return employee;
    }

    public Employee saveEmployee(Employee employee) {
        log.info("EmployeeService.saveEmployee() ,{} ", employee);
        return this.employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        log.info("EmployeeService.updateEmployee() ,{} ,{} ", id, employee);
        return this.employeeRepository.save(employee);
    }
}
