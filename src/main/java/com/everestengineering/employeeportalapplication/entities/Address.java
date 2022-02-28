package com.everestengineering.employeeportalapplication.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String addressLine1;
    private String addressLine2;
    @Pattern(regexp = "[A-Za-z]*",message = "Please enter only alpha characters.")
    private String city;
    @Pattern(regexp = "[A-Za-z]*",message = "Please enter only alpha characters.")
    private String state;
    private Long zipcode;
}