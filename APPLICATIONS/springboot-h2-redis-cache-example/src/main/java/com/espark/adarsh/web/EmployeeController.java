package com.espark.adarsh.web;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return ResponseEntity.ok(this.employeeService.getAllEmployee());
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.employeeService.getEmployee(id));
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity removeEmployee(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.employeeService.removeEmployee(id));
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(201).body(this.employeeService.saveEmployee(employee));
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return ResponseEntity.status(202).body(this.employeeService.updateEmployee(id, employee));
    }

}
