package com.espark.adarsh.web;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @QueryMapping
    public List<Employee> getAllEmployee() {
        return this.employeeService.getAllEmployee();
    }

    @QueryMapping
    public Employee getEmployee(@Argument Long id) {
        return this.employeeService.getEmployee(id);
    }

    @MutationMapping
    public Employee removeEmployee(@Argument("id") Long id) {
        return this.employeeService.removeEmployee(id);
    }

    @MutationMapping
    public Employee saveEmployee(@Argument Long id, @Argument String firstName, @Argument String lastName, @Argument String career) {
        return this.employeeService.saveEmployee(new Employee(id, firstName, lastName, career));
    }

    @MutationMapping
    public Employee updateEmployee(@Argument Long id, @Argument String firstName, @Argument String lastName, @Argument String career) {
        return this.employeeService.updateEmployee(id, new Employee(id, firstName, lastName, career));
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
