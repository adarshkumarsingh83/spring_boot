package com.espark.adarsh.bean;

import com.espark.adarsh.entities.EmployeeExperience;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeExperienceBean extends EmployeeExperience
        implements Serializable {

    EmployeeBean employeeBean;

    public EmployeeExperienceBean() {
    }

    public EmployeeExperienceBean(EmployeeExperience employeeExperience) {
        this.id = employeeExperience.getId();
        this.phoneNumber = employeeExperience.getPhoneNumber();
        this.start = employeeExperience.getStart();
        this.end = employeeExperience.getEnd();
        this.address1 = employeeExperience.getAddress1();
        this.address2 = employeeExperience.getAddress2();
        this.street = employeeExperience.getStreet();
        this.city = employeeExperience.getCity();
        this.state = employeeExperience.getState();
        this.country = employeeExperience.getCountry();
        this.zipCode = employeeExperience.getZipCode();
    }

    public EmployeeExperienceBean(EmployeeExperience employeeExperience, EmployeeBean employee) {
        this.id = employeeExperience.getId();
        this.phoneNumber = employeeExperience.getPhoneNumber();
        this.start = employeeExperience.getStart();
        this.end = employeeExperience.getEnd();
        this.address1 = employeeExperience.getAddress1();
        this.address2 = employeeExperience.getAddress2();
        this.street = employeeExperience.getStreet();
        this.city = employeeExperience.getCity();
        this.state = employeeExperience.getState();
        this.country = employeeExperience.getCountry();
        this.zipCode = employeeExperience.getZipCode();
        this.employeeBean = employee;
    }
}
