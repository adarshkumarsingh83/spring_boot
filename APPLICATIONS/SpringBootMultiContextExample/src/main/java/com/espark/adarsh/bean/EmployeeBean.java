package com.espark.adarsh.bean;

import com.espark.adarsh.entity.EmployeeEntity;

public class EmployeeBean {

    private String empId;
    private String empName;


    public EmployeeBean() {
    }

    public EmployeeBean(String empId, String empName) {
        this.empId = empId;
        this.empName = empName;
    }

    public EmployeeBean(EmployeeEntity employeeEntity) {
        this.empId = employeeEntity.getEmpId();
        this.empName = employeeEntity.getEmpName();
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
