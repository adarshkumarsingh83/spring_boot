package com.espark.adarsh.web;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ApplicationController {

    private EmployeeService employeeService;

    public ApplicationController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        log.info("Received request to fetch all employees");
       return this.employeeService.fetchEmployees();
    }
}
