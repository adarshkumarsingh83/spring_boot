package com.espark.adarsh.web;

import com.espark.adarsh.persistance.entity.Employee;
import com.espark.adarsh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    public Employee getEmployee(Long empId){
        return this.employeeService.getEmployee(empId);
    }

    public List<Employee> getEmployee(String empName){
        return this.employeeService.getEmployee(empName);
    }
}
