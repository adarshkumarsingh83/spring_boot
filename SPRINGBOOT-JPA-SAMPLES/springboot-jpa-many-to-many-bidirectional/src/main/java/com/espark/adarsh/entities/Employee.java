package com.espark.adarsh.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    protected Gender gender;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true)
    protected String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    protected Date dob;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_project",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private List<Projects> projects = new LinkedList<>();

    public Employee() {
    }

    public Employee(@NotNull @Size(max = 65) String firstName
            , @Size(max = 65) String lastName
            , Gender gender
            , @NotNull @Email @Size(max = 100) String email
            , Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
    }

    public void setProject(Projects project) {
        if (this.projects != null) {
            projects.add(project);
        } else {
            this.projects = Arrays.asList(project);
        }
    }
}