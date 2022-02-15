package com.everestengineering.employeeportalapplication.entities;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;


    @Column(name = "house_number")
    private String houseNumber;


    @Column(name = "street_name")
    private String streetName;


    @Column(name = "city", nullable = false)
    private String city;


    @Column(name = "state", nullable = false)
    private String state;


    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "zipcode", nullable = false)
    private Long zipcode;


    @Column(name = "address_type", nullable = false)
    private String addressType;

   }