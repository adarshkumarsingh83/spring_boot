package com.espark.adarsh.service;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.integration.EmployeeApiIntegrationService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeService {

   private final EmployeeApiIntegrationService employeeApiIntegrationService;

    public EmployeeService(EmployeeApiIntegrationService employeeApiIntegrationService) {
        this.employeeApiIntegrationService = employeeApiIntegrationService;
    }


    @PostConstruct
    public void init() {
        log.info("EmployeeApiIntegrationService Bean Initialized :: {}", employeeApiIntegrationService.getClass().getName());
        Employee employee=new Employee(null,"Adarsh Kumar singh","adarsh.kumar@singh");
        employee = employeeApiIntegrationService.saveEmployee(employee);
        log.info("Saved Employee Data :: {}", employee);

        List<Employee> employees = employeeApiIntegrationService.getEmployees();
        employees.forEach(e -> log.info("Employee Data :: {}", e));

        Employee fetchedEmployee = employeeApiIntegrationService.getEmployeeById(employee.getId());
        log.info("Fetched Employee Data By Id :: {}", fetchedEmployee);

        employee.setEmail("adarsh.kumar@singh.update.com");
        employee = employeeApiIntegrationService.updateEmployee(employee.getId(),employee);
        log.info("Updated Employee Data :: {}", employee);

        Employee deletedEmployee = employeeApiIntegrationService.deleteEmployeeById(employee.getId());
        log.info("Deleted Employee Data :: {}", deletedEmployee);

    }
}
