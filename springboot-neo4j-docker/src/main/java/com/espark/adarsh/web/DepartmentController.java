package com.espark.adarsh.web;

import com.espark.adarsh.enitity.Department;
import com.espark.adarsh.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/departments/{name}")
    public List<Department> getByName(@PathVariable("name") String name) {
        log.info("label=DepartmentController getByName()");
        return this.departmentService.findByName(name);
    }

    @GetMapping("/graph/{limit}")
    public List<Map<String, Object>> graph(@PathVariable("limit") Integer limit) {
        log.info("label=DepartmentController graph()");
        return this.departmentService.graph(limit);
    }

}
