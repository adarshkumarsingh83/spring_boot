package com.espark.adarsh.service;

import com.espark.adarsh.enitity.Department;
import com.espark.adarsh.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;


    @Transactional(readOnly = false)
    public Department save(Department department) {
        log.info("label=DepartmentService save()");
        return this.departmentRepository.save(department);
    }

    @Transactional(readOnly = false)
    public Department delete(Long departmentId) {
        log.info("label=DepartmentService delete()");
        Department department = this.departmentRepository.findById(departmentId).get();
        this.departmentRepository.delete(department);
        return department;
    }

    @Transactional(readOnly = true)
    public Department get(Long departmentId) {
        log.info("label=DepartmentService get()");
        return this.departmentRepository.findById(departmentId).get();
    }


    @Transactional(readOnly = true)
    public List<Department> findByName(String name) {
        log.info("label=DepartmentService findByName()");
        return this.departmentRepository.findByNameContaining(name);
    }

    @Transactional(readOnly = true)
    public List<Map<String,Object>> graph(int limit){
        log.info("label=DepartmentService graph()");
        return this.departmentRepository.graph(limit);
    }

}
