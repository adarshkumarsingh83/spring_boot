package com.espark.adarsh.web;

import com.espark.adarsh.bean.EmployeeBean;
import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


import javax.validation.Valid;
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
    public Employee saveEmployee(@Argument @Valid EmployeeBean employeeBean) {
        return this.employeeService.saveEmployee(employeeBean.getEmployee());
    }

    @MutationMapping
    public Employee updateEmployee(@Argument @Valid EmployeeBean employeeBean) {
        return this.employeeService.updateEmployee(employeeBean.getId(), employeeBean.getEmployee());
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
