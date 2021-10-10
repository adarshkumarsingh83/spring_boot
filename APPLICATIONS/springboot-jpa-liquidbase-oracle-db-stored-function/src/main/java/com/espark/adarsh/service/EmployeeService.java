package com.espark.adarsh.service;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.respository.EmployeeRepository;
import com.espark.adarsh.respository.EmployeeSpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeSpRepository employeeSpRepository;

    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new LinkedList<>();
        this.employeeRepository.findAll().forEach(employee -> employeeList.add(employee));
        return employeeList;
    }

    public List<Employee> getEmployeesByFunction() {
        return this.employeeSpRepository.getEmployeesByFunction();
    }


    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).get();
    }

    public Employee getEmployeeByFunction(Long id) {
        return  this.employeeSpRepository.getEmployeeByFunction(id).get(0);
    }

    public String getEmployeeStringByFunction(Long id) {
        return employeeRepository.getEmployeeData(id);
    }

    public Employee removeEmployee(Long id) {
        Employee employee = this.employeeRepository.findById(id).get();
        this.employeeRepository.deleteById(id);
        return employee;
    }

    public String removeEmployeeByFunction(Long id){
        return this.employeeRepository.deleteEmployeeByFunction(id);
    }

    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public String saveEmployeeByFunction(Employee employee) {
        return this.employeeRepository.saveEmployeeByFunction(employee.getEmpName(),employee.getEmpEmail());
    }

    public Employee updateEmployee(Long id, Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public String updateEmployeeByFunction(Long id, Employee employee) {
        return this.employeeRepository.updateEmployeeByFunction(id,employee.getEmpName(),employee.getEmpEmail());
    }
}
