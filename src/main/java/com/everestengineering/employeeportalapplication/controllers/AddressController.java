package com.everestengineering.employeeportalapplication.controllers;

import com.everestengineering.employeeportalapplication.entities.Address;
import com.everestengineering.employeeportalapplication.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/{employeeId}")
    public List<Address> getAllAddressesByEmployeeId(@PathVariable(name = "employeeId") Long employeeId){
        return addressService.getAddressesByEmployeeId(employeeId);
    }
    @PostMapping(" ")
    public Address addEmployeeAddress(@RequestBody Address address){
        return addressService.addEmployeeAddress(address);
    }
}
