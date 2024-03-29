package com.espark.adarsh.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    @Id
    @Column(name = "EMP_NO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMP_SEQ")
    @SequenceGenerator(sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, name = "EMP_SEQ")
    private Long empNo;

    @Column(name = "EMP_NAME")
    private String empName;

    @Column(name = "EMP_EMAIL")
    private String empEmail;

    public Employee() {
    }

    public Employee(String firstName, String empEmail) {
        this.empName = firstName;
        this.empEmail = empEmail;
    }

    public Employee(Long empNo, String firstName, String empEmail) {
        this.empNo = empNo;
        this.empName = firstName;
        this.empEmail = empEmail;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }
}

