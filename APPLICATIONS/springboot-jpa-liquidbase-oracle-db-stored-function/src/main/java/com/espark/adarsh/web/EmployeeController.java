package com.espark.adarsh.web;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return this.employeeService.getAllEmployee();
    }

    @GetMapping("/employees/fun")
    public List<Employee> getEmployeesByFunction() {
        return this.employeeService.getEmployeesByFunction();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return this.employeeService.getEmployee(id);
    }

    @GetMapping("/employee/fun/{id}")
    public Employee getEmployeeByFunction(@PathVariable("id") Long id) {
        return this.employeeService.getEmployeeByFunction(id);
    }

    @GetMapping("/employee/fun/str/{id}")
    public String getEmployeeData(@PathVariable("id") Long id) {
        return this.employeeService.getEmployeeStringByFunction(id);
    }

    @DeleteMapping("/employee/{id}")
    public Employee removeEmployee(@PathVariable("id") Long id) {
        return this.employeeService.removeEmployee(id);
    }

    @DeleteMapping("/employee/fun/{id}")
    public String removeEmployeeByFunction(@PathVariable("id") Long id) {
        return this.employeeService.removeEmployeeByFunction(id);
    }

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return this.employeeService.saveEmployee(employee);
    }

    @PostMapping("/employee/fun")
    public String saveEmployeeByFunction(@RequestBody Employee employee) {
        return this.employeeService.saveEmployeeByFunction(employee);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return this.employeeService.updateEmployee(id, employee);
    }

    @PutMapping("/employee/fun/{id}")
    public String updateEmployeeByFunction(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return this.employeeService.updateEmployeeByFunction(id, employee);
    }

}
