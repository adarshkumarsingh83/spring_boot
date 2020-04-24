package com.espark.adarsh.bean;

import com.espark.adarsh.entities.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeBean extends Employee implements Serializable {

    private List<ProjectsBean> projectsBeans;

    public EmployeeBean() {
    }

    public EmployeeBean(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.dob = employee.getDob();
        this.projectsBeans = null;
    }

    public EmployeeBean(Employee employee, List<ProjectsBean> list) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.dob = employee.getDob();
        this.projectsBeans = list;
    }

}
