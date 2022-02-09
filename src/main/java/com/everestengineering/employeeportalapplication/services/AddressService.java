package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Address;
import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.repositories.AddressRepository;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService{
    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;
    @Override
    public List<Address> getAddressesByEmployeeId(Long employeeId) {
        Optional<Employee> employee=employeeRepository.findById(employeeId);
        if(employee.isEmpty())
            return null;
        return addressRepository.findByEmployee(employee);


    }

    public Address addEmployeeAddress(Address address) {
        return addressRepository.save(address);
    }
}
