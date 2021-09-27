package com.espark.adarsh.bean;

import com.espark.adarsh.entity.Department;
import com.espark.adarsh.entity.Node;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericBean implements Node {

    private Long deptId;
    private String deptName;
    private Long parentDeptId;
    private List<EmployeeBean> employees;

    public GenericBean() {
    }

    public GenericBean(Department department) {
        this.deptId = department.getDeptId();
        this.deptName = department.getDeptName();
        this.parentDeptId = department.getParentDeptId();
    }

    public void setEmployees(EmployeeBean employee) {
        if (this.employees == null) {
            this.employees = new LinkedList<>();
        }
        this.employees.add(employee);
    }

    public void setEmployees(List<EmployeeBean> employees) {
        this.employees = employees;
    }
}

