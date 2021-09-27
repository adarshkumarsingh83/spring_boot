package com.espark.adarsh.bean;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.entity.Node;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeBean implements Node {

    private Long id;
    private String name;
    private String designation;
    private Long managerId;
    private Long deptId;
    private List<EmployeeBean> children;

    public EmployeeBean() {
    }

    public EmployeeBean(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.designation = employee.getDesignation();
        this.managerId = employee.getManagerId();
        this.deptId = employee.getDeptId();
    }

    public void setChildren(EmployeeBean child) {
        if (this.children == null) {
            this.children = new LinkedList<>();
        }
        this.children.add(child);
    }

    public void setChildren(List<EmployeeBean> children) {
        this.children = children;
    }
}

