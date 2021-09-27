package com.espark.adarsh.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String designation;
    private Long managerId;
    private Long deptId;

    public Employee() {
    }


}

