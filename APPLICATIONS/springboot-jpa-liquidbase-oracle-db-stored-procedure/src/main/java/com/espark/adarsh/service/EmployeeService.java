package com.espark.adarsh.service;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.respository.EmployeeRepository;
import com.espark.adarsh.respository.EmployeeSpRepository;
import liquibase.pro.packaged.E;
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


    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).get();
    }

    public Employee getEmployeeByProcedure(Long id) {

        //this.employeeSpRepository.getEmployeeByProcedure(id).get(0);
         this.employeeRepository.getEmployeeByProcedure(id);
         return null;
    }

    public String getEmployeeStringByProcedure(Long id) {
        return employeeRepository.getEmployeeStringByProcedure(id);
    }

    public Employee removeEmployee(Long id) {
        Employee employee = this.employeeRepository.findById(id).get();
        this.employeeRepository.deleteById(id);
        return employee;
    }

    public String removeEmployeeByProcedure(Long id){
        return this.employeeRepository.deleteEmployeeByProcedure(id);
    }

    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public String saveEmployeeByProcedure(Employee employee) {
        return this.employeeRepository.saveEmployeeByProcedure(employee.getEmpName(),employee.getEmpEmail());
    }

    public Employee updateEmployee(Long id, Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public String updateEmployeeByProcedure(Long id, Employee employee) {
        return this.employeeRepository.updateEmployeeByProcedure(id,employee.getEmpName(),employee.getEmpEmail());
    }
}
