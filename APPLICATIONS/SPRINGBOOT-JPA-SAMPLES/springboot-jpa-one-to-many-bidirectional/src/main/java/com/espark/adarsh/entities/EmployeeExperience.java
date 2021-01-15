package com.espark.adarsh.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "employee_experience")
public class EmployeeExperience implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Size(max = 100)
    protected String coyName;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    public EmployeeExperience() {

    }

    public EmployeeExperience(@Size(max = 100) String coyName
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
        this.coyName = coyName;
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
}