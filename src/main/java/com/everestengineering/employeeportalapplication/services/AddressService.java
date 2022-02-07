package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Address;
import com.everestengineering.employeeportalapplication.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AddressService implements IAddressService{
    private final AddressRepository addressRepository;
    @Override
    public Optional<Address> getAllAddressByEmployeeId(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address addAddress(Address address, Long id) {
        return null;
    }

    @Override
    public Address updateAddress(Address address, Long id, String type) {
        return null;
    }

    @Override
    public Address deleteAddress(Long id) {
        return null;
    }
}
