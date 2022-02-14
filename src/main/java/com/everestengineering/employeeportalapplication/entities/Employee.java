package com.everestengineering.employeeportalapplication.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "employee", uniqueConstraints = {
        @UniqueConstraint(name = "uc_employee_everest_email", columnNames = {"everest_email"})
})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Pattern(regexp = "[A-Za-z]*",message = "Please enter only alpha characters.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Pattern(regexp = "[A-Za-z]*",message = "Please enter only alpha characters.")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Email
    @Pattern(regexp = ".+@everest.engineering",message = "Please enter valid Everest email address")
    @Column(name = "everest_email", nullable = false)
    private String everestEmail;


    @Column(name = "password", nullable = false)
    private String password;

    @Email
    @Pattern(regexp = ".+@gmail.com",message = "Please enter valid gmail address")
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