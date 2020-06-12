package com.espark.adarsh.web;

import java.util.List;
import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return this.employeeService.getEmployees();
    }
}
