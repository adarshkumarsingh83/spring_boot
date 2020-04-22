package com.espark.adarsh.service;

import com.espark.adarsh.persistance.entity.Employee;
import com.espark.adarsh.persistance.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Employee getEmployee(Long empId){
        return this.repository.findById(empId).get();
    }

    public List<Employee> getEmployee(String empName){
        return this.repository.findByName(empName);
    }
}
