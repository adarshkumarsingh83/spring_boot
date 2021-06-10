package com.espark.adarsh.impl;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.respository.EmployeeRepository;
import com.espark.adarsh.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new LinkedList<>();
        this.employeeRepository.findAll().forEach(employee -> employeeList.add(employee));
        return employeeList;
    }

    @Override
    public Employee getEmployee(Long id) {
        return this.employeeRepository.findById(id).get();
    }

    public Employee removeEmployee(Long id) {
        Employee employee = this.employeeRepository.findById(id).get();
        this.employeeRepository.deleteById(id);
        return employee;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        return this.employeeRepository.save(employee);
    }
}
