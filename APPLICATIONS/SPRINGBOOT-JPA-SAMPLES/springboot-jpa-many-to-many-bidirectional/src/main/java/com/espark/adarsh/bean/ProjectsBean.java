package com.espark.adarsh.bean;

import com.espark.adarsh.entities.Projects;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectsBean extends Projects
        implements Serializable {

    List<EmployeeBean> employeeBeanList;

    public ProjectsBean() {
    }

    public ProjectsBean(Projects projects) {
        this.id = projects.getId();
        this.phoneNumber = projects.getPhoneNumber();
        this.start = projects.getStart();
        this.end = projects.getEnd();
        this.address1 = projects.getAddress1();
        this.address2 = projects.getAddress2();
        this.street = projects.getStreet();
        this.city = projects.getCity();
        this.state = projects.getState();
        this.country = projects.getCountry();
        this.zipCode = projects.getZipCode();
    }

    public ProjectsBean(Projects projects, List<EmployeeBean> employee) {
        this.id = projects.getId();
        this.phoneNumber = projects.getPhoneNumber();
        this.start = projects.getStart();
        this.end = projects.getEnd();
        this.address1 = projects.getAddress1();
        this.address2 = projects.getAddress2();
        this.street = projects.getStreet();
        this.city = projects.getCity();
        this.state = projects.getState();
        this.country = projects.getCountry();
        this.zipCode = projects.getZipCode();
        this.employeeBeanList = employee;
    }
}
