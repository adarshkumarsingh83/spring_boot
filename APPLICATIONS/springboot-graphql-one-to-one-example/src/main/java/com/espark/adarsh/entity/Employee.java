package com.espark.adarsh.entity;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
     private Long id;
     private String firstName;
     private String lastName;
     private String career;
    private Long salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "FK_ADDRESS_ID"))
    private Address address;

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

    public Employee(Long id, String firstName, String lastName, String career, Long salary, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.career = career;
        this.salary = salary;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

