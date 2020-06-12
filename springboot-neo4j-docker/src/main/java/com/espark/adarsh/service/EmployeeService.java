package com.espark.adarsh.service;

import com.espark.adarsh.enitity.Employee;
import com.espark.adarsh.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional(readOnly = false)
    public Employee save(Employee employee) {
        log.info("label=EmployeeService save()");
        return this.employeeRepository.save(employee);
    }

    @Transactional(readOnly = false)
    public Employee delete(Long employeeId) {
        log.info("label=EmployeeService delete()");
        Employee employee = this.employeeRepository.findById(employeeId).get();
        this.employeeRepository.delete(employee);
        return employee;
    }

    @Transactional(readOnly = true)
    public Employee get(Long employeeId) {
        log.info("label=EmployeeService get()");
        return this.employeeRepository.findById(employeeId).get();
    }

    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        log.info("label=EmployeeService getAll()");
        List<Employee> departments = new LinkedList<>();
        this.employeeRepository
                .findAll()
                .forEach(employee -> departments.add(employee));
        return departments;
    }

}
