package com.everestengineering.employeeportalapplication.entities;

import javax.persistence.*;
@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "number", nullable = false)
    private Long number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Employee id;


    @Column(name = "house_number")
    private String houseNumber;

    
    @Column(name = "street_name")
    private String streetName;

    
    @Column(name = "city")
    private String city;

  
    @Column(name = "state")
    private String state;


    @Column(name = "country")
    private String country;

    
    @Column(name = "zipcode")
    private String zipcode;


    @Column(name = "addresstype")
    private String addresstype;


}
