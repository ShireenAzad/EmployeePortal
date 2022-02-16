package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Employee;
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

    public Employee getEmployeeById(Long employeeId) throws EmployeeNotFoundException {

        if (employeeRepository.findById(employeeId).isEmpty()) {
            throw new EmployeeNotFoundException("EmployeeId " + employeeId + " doesn't exist");
        }
        return employeeRepository.getById(employeeId);
    }


}