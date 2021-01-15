package com.espark.adarsh.service;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.repsotiory.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.saveEmployee(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return this.employeeRepository.updateEmployee(employee);
    }

    @Override
    public Employee deleteEmployee(String employeeId) {
        return this.employeeRepository.deleteEmployee(employeeId);
    }

    @Override
    public Employee getEmployee(String employeeId) {
        return this.employeeRepository.getEmployee(employeeId);
    }

    @Override
    public List<Employee> getEmployees() {
        return this.employeeRepository.getEmployees();
    }
}
