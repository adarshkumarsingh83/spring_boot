package com.espark.adarsh.entity;

import com.espark.adarsh.bean.EmployeeBean;

public class EmployeeEntity {

    private String empId;
    private String empName;


    public EmployeeEntity() {
    }

    public EmployeeEntity(String empId, String empName) {
        this.empId = empId;
        this.empName = empName;
    }

    public EmployeeEntity(EmployeeBean employee) {
        this.empId = employee.getEmpId();
        this.empName = employee.getEmpName();
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
