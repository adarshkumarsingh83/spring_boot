package com.espark.adarsh;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployee() {
        return this.employeeService.getAllEmployees();
    }

}
