package com.espark.adarsh.bean;

import com.espark.adarsh.entity.Address;
import com.espark.adarsh.entity.Employee;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

public class EmployeeBean {

    @Digits(fraction = 0, integer = 100, message = "id for employee")
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private String career;

    private Long salary;

    private AddressBean addressBean;

    public EmployeeBean() {
    }

    public EmployeeBean(String firstName, String lastName, String career, Long salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.career = career;
        this.salary = salary;
    }

    public EmployeeBean(Long id, String firstName, String lastName, String career,Long salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.career = career;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Employee getEmployee() {
        return new Employee(this.id, this.firstName, this.lastName, this.career,this.salary,this.getAddressBean().getAddress());
    }

    public AddressBean getAddressBean() {
        return addressBean;
    }

    public void setAddressBean(AddressBean addressBean) {
        this.addressBean = addressBean;
    }
}