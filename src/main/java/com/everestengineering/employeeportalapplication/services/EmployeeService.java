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

    public EmployeesData findByName(String name, Pageable pageable) {

        if (employeeRepository.findByFirstNameContainingOrLastNameContaining(name, name,pageable).getContent().isEmpty()) {
            throw new EmployeesDataNotFoundException("Employee  with first name pattern of " + name + " or last name pattern of " + name + " not found.");
        } else {

            return new EmployeesData(employeeRepository.findByFirstNameContainingOrLastNameContaining(name,name, pageable));
        }


    }
}