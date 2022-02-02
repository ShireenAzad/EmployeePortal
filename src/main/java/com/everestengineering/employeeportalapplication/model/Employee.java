package com.everestengineering.employeeportalapplication.model;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {
    private Long empID;
    private String firstName;
    private String lastName;
    private String everestEmailId;
    private String password;
    private String personalEmailId;
    private Date dateOfBirth;
    private Date dateOfJoin;
    private String designation;
    private String priorExperienceInYears;
    private String bio;
    private EmployeeAddress employeeAddress;
    private EmployeeAddress permanentAddress;

}