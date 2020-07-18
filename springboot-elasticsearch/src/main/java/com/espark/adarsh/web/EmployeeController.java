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

    @GetMapping(value = "/employee")
    public List<Employee> getEmployees() {
        log.info("label=EmployeeController getEmployees()");
        return this.employeeService.getEmployees();
    }

    @PostMapping(value = "/employee")
    public Employee saveEmployees(@RequestBody Employee employee) {
        log.info("label=EmployeeController saveEmployees()");
        return this.employeeService.saveEmployees(employee);
    }

    @GetMapping(value = "/employee/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        log.info("label=EmployeeController getEmployee()");
        return this.employeeService.getEmployee(id);
    }

    @DeleteMapping("/employee/{id}")
    public Employee deleteEmployee(@PathVariable("id") Integer id) {
        log.info("label=EmployeeController deleteEmployee()");
        return this.employeeService.deleteEmployee(id);
    }

}
