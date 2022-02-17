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

    public Employee updateEmployee(long employeeId, Employee employee) {
        if (employeeRepository.existsById(employeeId)) {
            employee.setEmpId(employeeId);
            Employee existingEmployee=employeeRepository.getById(employeeId);
            employee.getPresentAddress().setId(existingEmployee.getPresentAddress().getId());
            employee.getPermanentAddress().setId(existingEmployee.getPermanentAddress().getId());

            employeeRepository.save(employee);
            return employee;
        } else {
            throw new EmployeeNotFoundException("Employee ID " + employeeId + " not present to update the details.");
        }


    }
}