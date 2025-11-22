package com.espark.adarsh.web;

import com.espark.adarsh.entities.Employee;
import com.espark.adarsh.service.EmployeeService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        log.info("Request Received For Get Employee By Id :: {}", id);
        return this.employeeService.getEmployeeByIdFunction().apply(id);
    }

    @GetMapping("/employees")
    public Iterable<Employee> getAllEmployees() {
        log.info("Request Received For Get All Employees");
        return this.employeeService.getEmployeesFunction().get();
    }

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        log.info("Request Received For Save Employee :: {}", employee);
        return this.employeeService.getSaveEmployeeFunction().apply(employee);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        log.info("Request Received For Update Employee Id :: {} Data :: {}", id, employee);
        return this.employeeService.getUpdateEmployeeFunction().apply(id, employee);
    }

    @DeleteMapping("/employee/{id}")
    public Employee deleteEmployee(@PathVariable("id") Long id) {
        log.info("Request Received For Delete Employee By Id :: {}", id);
        return this.employeeService.getDeleteEmployeeByIdFunction().apply(id);
    }
}
