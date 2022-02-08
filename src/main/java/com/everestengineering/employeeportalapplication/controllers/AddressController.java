package com.everestengineering.employeeportalapplication.controllers;

import com.everestengineering.employeeportalapplication.entities.Address;
import com.everestengineering.employeeportalapplication.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class AddressController {
    private final AddressService addressService;
    @GetMapping(value = "/{id}/addresses")
    public Optional<Address> getAllAddressesByEmployeeId(@PathVariable(value = "employee_id") Long id){
        return addressService.getAllAddressByEmployeeId(id);
    }

}
