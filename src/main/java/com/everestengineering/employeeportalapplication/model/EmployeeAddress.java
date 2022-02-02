package com.everestengineering.employeeportalapplication.model;

import lombok.Data;

@Data
public class EmployeeAddress {
    private String houseNumber;
    private String streetName;
    private String city;
    private String state;
    private String country;
    private String zipCode;

}
