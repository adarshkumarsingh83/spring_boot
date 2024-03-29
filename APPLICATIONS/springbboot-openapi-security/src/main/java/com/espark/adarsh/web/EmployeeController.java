package com.espark.adarsh.web;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.exception.EmployeeNotFoundException;
import com.espark.adarsh.respository.EmployeeRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

@RestController
@SecurityRequirement(name = "epsark-api")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        logger.info("EmployeeController getAllEmployee() ");
        List<Employee> employeeList = new LinkedList<>();
        this.employeeRepository.findAll().forEach(employee -> employeeList.add(employee));
        return employeeList;
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        return this.employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("employee not found"));
    }

    @DeleteMapping("/employee/{id}")
    public Employee removeEmployee(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("employee not found"));
        this.employeeRepository.deleteById(id);
        return employee;
    }

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) throws EmployeeNotFoundException {
        if (this.employeeRepository.existsById(id)) {
            return this.employeeRepository.save(employee);
        } else {
            throw new EmployeeNotFoundException("employee not found");
        }
    }

}
