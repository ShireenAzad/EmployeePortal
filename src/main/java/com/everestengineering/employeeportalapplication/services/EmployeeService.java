package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.exceptions.EmployeeNotFoundException;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void delete(long employeeId) {

        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not Found");
        }

    }
}