package com.espark.adarsh.entity;

import com.espark.adarsh.entity.convertors.AttributeMapConverter;
import com.espark.adarsh.entity.convertors.PhoneListConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String career;

    private Long salary;

    @Column(name = "doj", columnDefinition = "DATE")
    private LocalDate doj;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Convert(converter = AttributeMapConverter.class)
    private Map<String, String> attributes;


    @Convert(converter = PhoneListConverter.class)
    private List<String> phone;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String career, Long salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.career = career;
        this.salary = salary;
    }

    public Employee(Long id, String firstName, String lastName, String career, Long salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.career = career;
        this.salary = salary;
    }

    public Employee(Long id, String firstName, String lastName, String career, Long salary, LocalDate doj, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.career = career;
        this.salary = salary;
        this.doj = doj;
        this.gender = gender;
    }

    public Employee(Long id, String firstName, String lastName, String career, Long salary, LocalDate doj, Gender gender, Map<String, String> attributes, List<String> phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.career = career;
        this.salary = salary;
        this.doj = doj;
        this.gender = gender;
        this.attributes = attributes;
        this.phone = phone;
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

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}

