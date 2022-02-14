package com.everestengineering.employeeportalapplication.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long id;


    @Column(name = "first_name", nullable = false)
    private String firstName;


    @Column(name = "last_name", nullable = false)
    private String lastName;


    @Column(name = "everest_email", nullable = false)
    private String everestEmail;


    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_join")
    private LocalDate dateOfJoin;


    @Column(name = "designation", nullable = false)
    private String designation;


    @Column(name = "experience")
    private String experience;


    @Column(name = "bio")
    private String bio;

}