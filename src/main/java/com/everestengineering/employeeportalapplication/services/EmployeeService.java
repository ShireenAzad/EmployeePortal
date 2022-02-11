package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.exceptions.EmployeesDataNotFoundException;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> findByName(String firstName, String lastName) {

        if (employeeRepository.findByFirstNameAndLastName(firstName, lastName).size() != 0) {
            return employeeRepository.findByFirstNameAndLastName(firstName, lastName);
        } else {
            throw new EmployeesDataNotFoundException("Employee  with first name pattern of " + firstName + " and last name pattern of " + lastName + " not found.");
        }


    }
}