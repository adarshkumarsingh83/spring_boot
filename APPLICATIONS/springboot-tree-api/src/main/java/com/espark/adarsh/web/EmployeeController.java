package com.espark.adarsh.web;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.DepartmentEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    DepartmentEmployeeService departmentEmployeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return departmentEmployeeService.getAllEmployee();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return this.departmentEmployeeService.getEmployee(id);
    }


    @GetMapping("/employees/manager/{id}")
    public List<Employee> getEmployeeByManagerId(@PathVariable("id") Long id) {
        return this.departmentEmployeeService.getEmployeeByManagerId(id);
    }

    @GetMapping("/employees/department/{id}")
    public List<Employee> getEmployeeByDepartmentId(@PathVariable("id") Long id) {
        return this.departmentEmployeeService.getEmployeeByDepartmentId(id);
    }

    @DeleteMapping("/employees/{id}")
    public Employee removeEmployee(@PathVariable("id") Long id) {
        return this.departmentEmployeeService.removeEmployee(id);
    }

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return this.departmentEmployeeService.saveEmployee(employee);
    }

    @PostMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return this.departmentEmployeeService.updateEmployee(id, employee);
    }

    @GetMapping("/employee/manger/{deptId}/{designation}")
    public List<Employee> findManagerBytDeptId(@PathVariable("deptId") Long deptId, @PathVariable("designation") String designation) {
        return this.departmentEmployeeService.findManagerBytDeptId(deptId, designation);
    }

}
