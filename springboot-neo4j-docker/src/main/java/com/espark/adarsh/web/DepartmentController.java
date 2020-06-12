package com.espark.adarsh.web;

import com.espark.adarsh.enitity.Department;
import com.espark.adarsh.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;


    @PostMapping("/department")
    public Department save(@RequestBody Department department) {
        log.info("label=DepartmentController save()");
        return this.departmentService.save(department);
    }

    @DeleteMapping("/department/{departmentId}")
    public Department delete(@PathVariable("departmentId") Long departmentId) {
        log.info("label=DepartmentController delete()");
        return this.departmentService.delete(departmentId);
    }

    @GetMapping("/department/{departmentId}")
    public Department get(@PathVariable("departmentId") Long departmentId) {
        log.info("label=DepartmentController get()");
        return this.departmentService.get(departmentId);
    }

    @GetMapping("/departments")
    public List<Department> getAll() {
        log.info("label=DepartmentController getAll()");
        return this.departmentService.getAll();
    }

}
