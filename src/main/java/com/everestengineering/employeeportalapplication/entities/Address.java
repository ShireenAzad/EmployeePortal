package com.everestengineering.employeeportalapplication.entities;

import lombok.Data;

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

    @Lob
    @Column(name = "house_number")
    private String houseNumber;

    @Lob
    @Column(name = "street_name")
    private String streetName;

    @Lob
    @Column(name = "city")
    private String city;

    @Lob
    @Column(name = "state")
    private String state;

    @Lob
    @Column(name = "country")
    private String country;

    @Lob
    @Column(name = "zipcode")
    private String zipcode;

    @Lob
    @Column(name = "addresstype")
    private String addresstype;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}