package com.espark.adarsh.bean;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.entity.Gender;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
public class EmployeeBean {

    @Digits(fraction = 0, integer = 100, message = "id for employee")
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private String career;

    private Long salary;

    private LocalDate doj;

    private Gender gender;

    private List<String> phone;

    private Map<String,String> attributes;

    public EmployeeBean() {
    }

    public EmployeeBean(Long id, String firstName, String lastName, String career, Long salary, LocalDate doj, Gender gender, Map<String,String> attributes) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.career = career;
        this.salary = salary;
        this.doj = doj;
        this.gender = gender;
        this.attributes = attributes;
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


    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public  Map<String,String> getAttributes() {
        return attributes;
    }

    public void setAttributes( Map<String,String> attributes) {
        this.attributes = attributes;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public Employee getEmployee() {
        return new Employee(this.id, this.firstName, this.lastName, this.career,this.salary,this.doj,this.gender,this.attributes,this.phone);
    }
}