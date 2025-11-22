package com.espark.adarsh.integration;

import com.espark.adarsh.bean.Employee;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

@Component
public interface EmployeeApiIntegrationService {

    @PostExchange("http://localhost:8080/employee")
    Employee saveEmployee(@RequestBody Employee employee);

    @GetExchange("http://localhost:8080/employees")
    List<Employee> getEmployees();

    @GetExchange("http://localhost:8080/employee/{id}")
    Employee getEmployeeById(@PathVariable Long id);

    @PutExchange("http://localhost:8080/employee/{id}")
    Employee updateEmployee(@PathVariable Long id,@RequestBody Employee employee);

    @DeleteExchange("http://localhost:8080/employee/{id}")
    Employee deleteEmployeeById(@PathVariable Long id);


}
