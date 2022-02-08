package com.everestengineering.employeeportalapplication.entities;

import javax.persistence.*;

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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getAddresstype() {
        return addresstype;
    }

    public void setAddresstype(String addresstype) {
        this.addresstype = addresstype;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Employee getId() {
        return id;
    }

    public void setId(Employee id) {
        this.id = id;
    }
}