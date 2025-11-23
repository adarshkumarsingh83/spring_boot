package com.espark.adarsh.web;

import com.espark.adarsh.bean.ApiResponse;
import com.espark.adarsh.entities.Employee;
import com.espark.adarsh.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employee/{id}" ,version = "1.0")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        log.info("Request Received For Get Employee By Id :: {}", id);
        return this.employeeService.getEmployeeByIdFunction().apply(id);
    }

    @GetMapping(path  = "/employees",version = "1.0")
    public Iterable<Employee> getAllEmployees() {
        log.info("Request Received For Get All Employees");
        return this.employeeService.getEmployeesFunction().get();
    }

    @PostMapping(path = "/employee" ,version = "1.0")
    public Employee saveEmployee(@RequestBody Employee employee) {
        log.info("Request Received For Save Employee :: {}", employee);
        return this.employeeService.getSaveEmployeeFunction().apply(employee);
    }

    @PutMapping(path = "/employee/{id}",version = "1.0")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        log.info("Request Received For Update Employee Id :: {} Data :: {}", id, employee);
        return this.employeeService.getUpdateEmployeeFunction().apply(id, employee);
    }

    @DeleteMapping(path = "/employee/{id}" ,version = "1.0")
    public Employee deleteEmployee(@PathVariable("id") Long id) {
        log.info("Request Received For Delete Employee By Id :: {}", id);
        return this.employeeService.getDeleteEmployeeByIdFunction().apply(id);
    }

    @GetMapping(path  = "/employees", version = "1.1")
    public ApiResponse<Iterable<Employee>> getAllEmployee() {
        log.info("Request Received For Get All Employees");
        ApiResponse<Iterable<Employee>> response = new ApiResponse();
        response.setData(this.employeeService.getEmployeesFunction().get());
        return response;
    }
}
