package com.espark.adarsh.controller;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    // Get a all Employee
    @GetMapping("/employee")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    // Create a new Employee
    @PostMapping("/employee")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }


    // Get a Single Employee
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long empId) {
        return this.employeeService.getEmployeeById(empId);
    }

    // Update a Employee
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Long empId,
                                   @Valid @RequestBody Employee employeeDetails) {
        return this.employeeService.updateEmployee(empId, employeeDetails);
    }

    // Delete a Employee
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long empId) {
        Employee employee = this.employeeService.deleteEmployee(empId);
        return ResponseEntity.ok().body(employee);
    }

}