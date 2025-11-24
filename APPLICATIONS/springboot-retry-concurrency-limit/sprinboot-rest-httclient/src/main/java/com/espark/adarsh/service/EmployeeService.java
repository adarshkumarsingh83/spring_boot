package com.espark.adarsh.service;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.exception.EsparkApiException;
import com.espark.adarsh.integration.EmployeeApiIntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import java.util.List;

@Slf4j
@Service
public class EmployeeService {

    private final EmployeeApiIntegrationService employeeApiIntegrationService;

    public EmployeeService(EmployeeApiIntegrationService employeeApiIntegrationService) {
        this.employeeApiIntegrationService = employeeApiIntegrationService;
    }


    @ConcurrencyLimit(limit = 10)
    @Retryable(includes = {EsparkApiException.class,HttpServerErrorException.ServiceUnavailable.class, ResourceAccessException.class}
            , maxRetries = 3, delay = 1500)
    public Employee createEmployee(Employee employee) {
        try {
            log.info("Creating Employee: {}", employee);
            return employeeApiIntegrationService.saveEmployee(employee);
        } catch (Exception e) {
           log.error("Error occurred while creating employee with employee {}: {}", employee, e.getMessage());
            throw new EsparkApiException("Failed to creating employee with employee " + employee, e);
        }
    }

    @ConcurrencyLimit(limit = 10)
    @Retryable(includes = {EsparkApiException.class,
            HttpServerErrorException.ServiceUnavailable.class, ResourceAccessException.class}
            , maxRetries = 3, delay = 1500)
    public List<Employee> fetchEmployees() {
        try {
            log.info("Fetching Employees");
            return employeeApiIntegrationService.getEmployees();
        } catch (Exception e) {
            log.error("Error occurred while fetching employee  {} ", e.getMessage());
            throw new EsparkApiException("Failed to fetch employee ", e);
        }
    }

    @ConcurrencyLimit(limit = 10)
    @Retryable(includes = {EsparkApiException.class,
            HttpServerErrorException.ServiceUnavailable.class, ResourceAccessException.class},
            maxRetries = 3, delay = 1500)
    public Employee fetchEmployeeById(Long id) {
        try {
            log.info("Fetching Employee By Id: {}", id);
            return employeeApiIntegrationService.getEmployeeById(id);
        } catch (Exception e) {
            log.error("Error occurred while fetching employee with id {}: {}", id, e.getMessage());
            throw new EsparkApiException("Failed to fetching employee with id " + id, e);
        }
    }

    @ConcurrencyLimit(limit = 10)
    @Retryable(includes = {EsparkApiException.class,
            HttpServerErrorException.ServiceUnavailable.class, ResourceAccessException.class}
            , maxRetries = 3, delay = 1500)
    public Employee modifyEmployee(Long id, Employee employee) {
        try {
            log.info("Modifying Employee By Id: {}", id);
            return employeeApiIntegrationService.updateEmployee(id, employee);
        } catch (Exception e) {
            log.error("Error occurred while updating employee with id {}: {}", id, e.getMessage());
            throw new EsparkApiException("Failed to updating employee with id " + id, e);
        }
    }

    @ConcurrencyLimit(limit = 10)
    @Retryable(includes = {EsparkApiException.class,
            HttpServerErrorException.ServiceUnavailable.class, ResourceAccessException.class}
            , maxRetries = 3, delay = 1500)
    public Employee removeEmployeeById(Long id) {
        try {
            log.info("Removing Employee By Id: {}", id);
            return employeeApiIntegrationService.deleteEmployeeById(id);
        } catch (Exception e) {
            log.error("Error occurred while removing employee with id {}: {}", id, e.getMessage());
            throw new EsparkApiException("Failed to remove employee with id " + id, e);
        }
    }
}
