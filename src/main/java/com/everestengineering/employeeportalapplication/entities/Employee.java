package com.everestengineering.employeeportalapplication.entities;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Lob
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Lob
    @Column(name = "everest_email", nullable = false)
    private String everestEmail;

    @Lob
    @Column(name = "password", nullable = false)
    private String password;

    @Lob
    @Column(name = "personal_email", nullable = false)
    private String personalEmail;

    @Lob
    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Lob
    @Column(name = "date_of_join")
    private String dateOfJoin;

    @Lob
    @Column(name = "designation", nullable = false)
    private String designation;

    @Lob
    @Column(name = "experience")
    private String experience;

    @Lob
    @Column(name = "bio")
    private String bio;


}