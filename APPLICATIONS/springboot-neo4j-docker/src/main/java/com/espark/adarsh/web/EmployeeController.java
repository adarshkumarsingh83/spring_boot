package com.espark.adarsh.web;

import com.espark.adarsh.enitity.Employee;
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


    @PostMapping("/employee")
    public Employee save(@RequestBody Employee employee) {
        log.info("label=EmployeeController save()");
        return this.employeeService.save(employee);
    }

    @DeleteMapping("/employee/{employeeId}")
    public Employee delete(@PathVariable("employeeId") Long employeeId) {
        log.info("label=EmployeeController delete()");
        return this.employeeService.delete(employeeId);
    }

    @GetMapping("/employee/{employeeId}")
    public Employee get(@PathVariable("employeeId") Long employeeId) {
        log.info("label=EmployeeController get()");
        return this.employeeService.get(employeeId);
    }

    @GetMapping("/employees")
    public List<Employee> getAll() {
        log.info("label=EmployeeController getAll()");
        return this.employeeService.getAll();
    }

}
