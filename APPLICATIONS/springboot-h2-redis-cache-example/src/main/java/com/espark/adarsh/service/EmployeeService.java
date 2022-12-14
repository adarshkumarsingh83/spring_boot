package com.espark.adarsh.service;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.exception.EmployeeNotFoundException;
import com.espark.adarsh.respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Cacheable(value = "Employee")
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new LinkedList<>();
        this.employeeRepository.findAll().forEach(employee -> employeeList.add(employee));
        return employeeList;
    }

    @Cacheable(value = "Employee", key = "#id")
    public Employee getEmployee(Long id) {
        return this.employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("employee not found"));
    }

    @CachePut(value = "Employee", key = "#id")
    public Employee removeEmployee(Long id) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("employee not found"));
        this.employeeRepository.deleteById(id);
        return employee;
    }

    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @CachePut(value = "Employee", key = "#id")
    public Employee updateEmployee(Long id, Employee employee) {
        Employee employeeToUpdate = this.employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("employee not found"));
        employeeToUpdate.setFirstName(employee.getFirstName());
        employeeToUpdate.setLastName(employee.getLastName());
        employeeToUpdate.setCareer(employee.getCareer());
        return this.employeeRepository.save(employeeToUpdate);
    }
}
