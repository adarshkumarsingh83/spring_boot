package com.espark.adarsh.enitity;


import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RelationshipEntity(type = "WORKS")
public class Work {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Employee employee;

    @EndNode
    private Department department;

    private List<String> work = new LinkedList<>();

    public void addWorkName(String name) {
        this.work.add(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<String> getWork() {
        return work;
    }

    public void setWork(List<String> work) {
        this.work = work;
    }
}
