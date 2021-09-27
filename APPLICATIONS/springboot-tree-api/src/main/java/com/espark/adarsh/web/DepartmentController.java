package com.espark.adarsh.web;

import com.espark.adarsh.entity.Department;
import com.espark.adarsh.respository.DepartmentRepository;
import com.espark.adarsh.service.DepartmentEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class DepartmentController {


    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DepartmentEmployeeService departmentEmployeeService;

    @GetMapping("/departments")
    public List<Department> getAllDepartment() {
        return departmentEmployeeService.getAllDepartment();
    }

    @GetMapping("/department/{id}")
    public Department getDepartment(@PathVariable("id") Long id) {
        return this.departmentEmployeeService.getDepartment(id);
    }

    @DeleteMapping("/department/{id}")
    public Department removeDepartment(@PathVariable("id") Long id) {
        return this.departmentEmployeeService.removeDepartment(id);
    }

    @PostMapping("/department")
    public Department saveDepartment(@RequestBody Department department) {
        return this.departmentEmployeeService.saveDepartment(department);
    }

    @PostMapping("/department/{id}")
    public Department updateDepartment(@PathVariable("id") Long id, @RequestBody Department department) {
        return this.departmentEmployeeService.updateDepartment(id, department);
    }

    @GetMapping("/departments/child/{id}")
    public List<Department> getChildDepartment(@PathVariable("id") Long id) {
        return this.departmentEmployeeService.getChildDepartment(id);
    }

}
