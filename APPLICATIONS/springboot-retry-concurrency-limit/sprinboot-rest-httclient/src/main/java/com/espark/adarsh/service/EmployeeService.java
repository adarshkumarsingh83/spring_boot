package com.espark.adarsh.service;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.integration.EmployeeApiIntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Slf4j
@Service
public class EmployeeService {

    private final EmployeeApiIntegrationService employeeApiIntegrationService;

    public EmployeeService(EmployeeApiIntegrationService employeeApiIntegrationService) {
        this.employeeApiIntegrationService = employeeApiIntegrationService;
    }


    @Retryable(includes = {HttpServerErrorException.ServiceUnavailable.class}
            , maxRetries = 3, delay = 500)
    public Employee createEmployee(Employee employee) {
        log.info("Creating Employee: {}", employee);
        return employeeApiIntegrationService.saveEmployee(employee);
    }

    @Retryable(includes = {HttpServerErrorException.ServiceUnavailable.class}
            , maxRetries = 3, delay = 500)
    public List<Employee> fetchEmployees() {
        log.info("Fetching Employees");
        return employeeApiIntegrationService.getEmployees();
    }

    @Retryable(includes = {HttpServerErrorException.ServiceUnavailable.class}
            , maxRetries = 3, delay = 500)
    public Employee fetchEmployeeById(Long id) {
        log.info("Fetching Employee By Id: {}", id);
        return employeeApiIntegrationService.getEmployeeById(id);
    }

    @Retryable(includes = {HttpServerErrorException.ServiceUnavailable.class}
            , maxRetries = 3, delay = 500)
    public Employee modifyEmployee(Long id, Employee employee) {
        log.info("Modifying Employee By Id: {}", id);
        return employeeApiIntegrationService.updateEmployee(id, employee);
    }

    @Retryable(includes = {HttpServerErrorException.ServiceUnavailable.class}
            , maxRetries = 3, delay = 500)
    public Employee removeEmployeeById(Long id) {
        log.info("Removing Employee By Id: {}", id);
        return employeeApiIntegrationService.deleteEmployeeById(id);
    }


}
