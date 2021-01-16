package com.espark.adarsh.web;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        log.info("EmployeeController.getAllEmployee()");
        return this.employeeService.getAllEmployee();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        log.info("EmployeeController.getEmployee(), {}", id);
        return this.employeeService.getEmployee(id);
    }

    @DeleteMapping("/employee/{id}")
    public Employee removeEmployee(@PathVariable("id") Long id) {
        log.info("EmployeeController.removeEmployee(), {}", id);
        return this.employeeService.removeEmployee(id);
    }

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        log.info("EmployeeController.saveEmployee() ,{} ", employee);
        return this.employeeService.saveEmployee(employee);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        log.info("EmployeeController.updateEmployee() ,{} ,{} ", id, employee);
        return this.employeeService.updateEmployee(id, employee);
    }
}