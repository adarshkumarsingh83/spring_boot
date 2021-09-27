package com.espark.adarsh.bean;

import com.espark.adarsh.entity.Department;
import com.espark.adarsh.entity.Node;
import lombok.Data;


@Data
public class DepartmentBean implements Node {

    private Long deptId;
    private String deptName;
    private Long parentDeptId;

    public DepartmentBean() {
    }

    public DepartmentBean(Department department) {
        this.deptId = department.getDeptId();
        this.deptName = department.getDeptName();
        this.parentDeptId = department.getParentDeptId();
    }
}

