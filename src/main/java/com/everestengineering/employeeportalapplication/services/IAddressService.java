package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Address;

import java.util.Optional;

public interface IAddressService {
    Optional<Address> getAllAddressByEmployeeId(Long id);
    Address addAddress(Address address,Long id);
    Address updateAddress(Address address, Long id, String type);
    Address deleteAddress(Long id);

}
