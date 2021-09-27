package com.espark.adarsh.service;

import com.espark.adarsh.entity.Department;
import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.respository.DepartmentRepository;
import com.espark.adarsh.respository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class DepartmentEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    public List<Department> getChildDepartment(Long id) {
        return this.departmentRepository.findByParentDeptId(id);
    }

    public List<Department> getAllDepartment() {
        List<Department> departments = new LinkedList<>();
        this.departmentRepository.findAll().forEach(department -> departments.add(department));
        return departments;
    }

    public Department getDepartment(Long id) {
        return this.departmentRepository.findById(id).get();
    }

    public Department removeDepartment(Long id) {
        Department department = this.departmentRepository.findById(id).get();
        this.departmentRepository.deleteById(id);
        return department;
    }

    public Department saveDepartment(Department department) {
        return this.departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department department) {
        return this.departmentRepository.save(department);
    }

    public List<Employee> findManagerBytDeptId(Long deptId, String designation) {
        return this.employeeRepository.findManagerBytDeptId(deptId, designation);
    }

    public Employee getEmployee(Long id) {
        return this.employeeRepository.findById(id).get();
    }

    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new LinkedList<>();
        this.employeeRepository.findAll().forEach(employee -> employeeList.add(employee));
        return employeeList;
    }


    public List<Employee> getEmployeeByManagerId(Long id) {
        return this.employeeRepository.findByManagerId(id);
    }

    public List<Employee> getEmployeeByDepartmentId(Long id) {
        return this.employeeRepository.findByDepartmentId(id);
    }

    public Employee removeEmployee(Long id) {
        Employee employee = this.employeeRepository.findById(id).get();
        this.employeeRepository.deleteById(id);
        return employee;
    }

    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        return this.employeeRepository.save(employee);
    }
}
