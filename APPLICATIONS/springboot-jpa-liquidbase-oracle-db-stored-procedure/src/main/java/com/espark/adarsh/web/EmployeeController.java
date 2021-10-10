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

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return this.employeeService.getEmployee(id);
    }

    @GetMapping("/employee/pro/{id}")
    public Employee getEmployeeByProcedure(@PathVariable("id")Long id) {
        return this.employeeService.getEmployeeByProcedure(id);
    }

    @GetMapping("/employee/pro/str/{id}")
    public String getEmployeeData(@PathVariable("id") Long id) {
        return this.employeeService.getEmployeeStringByProcedure(id);
    }

    @DeleteMapping("/employee/{id}")
    public Employee removeEmployee(@PathVariable("id") Long id) {
        return this.employeeService.removeEmployee(id);
    }

    @DeleteMapping("/employee/pro/{id}")
    public String removeEmployeeByProcedure(@PathVariable("id") Long id) {
        return this.employeeService.removeEmployeeByProcedure(id);
    }

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return this.employeeService.saveEmployee(employee);
    }

    @PostMapping("/employee/pro")
    public String saveEmployeeByProcedure(@RequestBody Employee employee) {
        return this.employeeService.saveEmployeeByProcedure(employee);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return this.employeeService.updateEmployee(id, employee);
    }

    @PutMapping("/employee/pro/{id}")
    public String updateEmployeeByProcedure(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return this.employeeService.updateEmployeeByProcedure(id, employee);
    }

}
