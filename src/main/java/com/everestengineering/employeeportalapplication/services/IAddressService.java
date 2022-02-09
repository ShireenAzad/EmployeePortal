package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Address;

import java.util.List;

public interface IAddressService {
   List<Address> getAddressesByEmployeeId(Long employeeId);


}
