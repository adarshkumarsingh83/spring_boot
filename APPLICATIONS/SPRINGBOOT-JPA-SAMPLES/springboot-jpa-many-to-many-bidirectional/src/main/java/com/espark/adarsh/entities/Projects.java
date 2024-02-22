package com.espark.adarsh.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "projects")
public class Projects implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Size(max = 100)
    protected String name;

    @Column(name = "phone_number")
    @Size(max = 15)
    protected String phoneNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "start")
    protected Date start;

    @Temporal(TemporalType.DATE)
    @Column(name = "end")
    protected Date end;

    @Size(max = 100)
    protected String address1;

    @Size(max = 100)
    protected String address2;

    @Size(max = 100)
    protected String street;

    @Size(max = 100)
    protected String city;

    @Size(max = 100)
    protected String state;

    @Size(max = 100)
    protected String country;

    @Column(name = "zip_code")
    @Size(max = 32)
    protected String zipCode;

    @ManyToMany(mappedBy = "projects", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Employee> employees = new LinkedList<>();

    public Projects() {

    }

    public Projects(@Size(max = 100) String name
            , @Size(max = 15) String phoneNumber
            , Date start
            , Date end
            , @Size(max = 100) String address1
            , @Size(max = 100) String address2
            , @Size(max = 100) String street
            , @Size(max = 100) String city
            , @Size(max = 100) String state
            , @Size(max = 100) String country
            , @Size(max = 32) String zipCode) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.start = start;
        this.end = end;
        this.address1 = address1;
        this.address2 = address2;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public void setEmployee(Employee employees) {
        if (this.employees != null) {
            this.employees.add(employees);
        } else {
            this.employees = Arrays.asList(employees);
        }
    }
}