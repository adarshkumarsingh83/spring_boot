package com.espark.adarsh.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    @Size(max = 65)
    @Column(name = "first_name")
    protected String firstName;

    @Size(max = 65)
    @Column(name = "last_name")
    protected String lastName;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true)
    protected String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    protected Date dob;


    public Employee() {
    }

    public Employee(@NotNull @Size(max = 65) String firstName
            , @Size(max = 65) String lastName
            , @NotNull @Email @Size(max = 100) String email
            , Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
    }

}