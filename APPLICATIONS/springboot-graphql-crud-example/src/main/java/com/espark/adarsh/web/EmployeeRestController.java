/*
package com.espark.adarsh.web;

import com.espark.adarsh.bean.EmployeeBean;
import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.filter.EmployeeFilter;
import com.espark.adarsh.service.EmployeeService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeRestController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return this.employeeService.getAllEmployee();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") Long id, DataFetchingEnvironment env) {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String val = attributes.getRequest().getHeader("name");
        return this.employeeService.getEmployee(id);
    }

    @DeleteMapping("/employee/{id")
    public Employee removeEmployee(@PathVariable("id") Long id) {
        return this.employeeService.removeEmployee(id);
    }

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody @Valid EmployeeBean employeeBean) {
        return this.employeeService.saveEmployee(employeeBean.getEmployee());
    }

    @PutMapping("/employee")
    public Employee updateEmployee(@RequestBody @Valid EmployeeBean employeeBean) {
        return this.employeeService.updateEmployee(employeeBean.getId(), employeeBean.getEmployee());
    }


    @PostMapping
    public Iterable<Employee> employeesFilter(@RequestBody EmployeeFilter filter) {
       return this.employeeService.employeesFilter(filter);
    }



}
*/
