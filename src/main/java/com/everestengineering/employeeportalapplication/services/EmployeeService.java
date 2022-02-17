package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.entities.EmployeesData;
import com.everestengineering.employeeportalapplication.exceptions.EmployeesDataNotFoundException;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeesData findByName(String firstName, String lastName, Pageable pageable) {

        if (employeeRepository.findByFirstNameAndLastName(firstName, lastName,pageable).getContent().isEmpty()){
            throw new EmployeesDataNotFoundException("Employee  with first name pattern of " + firstName + " and last name pattern of " + lastName + " not found.");
        }
        else
        {

            return new EmployeesData(employeeRepository.findByFirstNameAndLastName(firstName, lastName, pageable));
        }


    }
}