package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.exceptions.EmployeeNotFoundException;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Employee updateEmployee(long employeeId, Employee employee) {
        Optional<Employee> employeeOldDetails = employeeRepository.findById(employeeId);
        if (employeeOldDetails.isPresent()) {
            Employee employeeExistingWithEverestEmail = employeeRepository.findByEverestEmailId(employee.getEverestEmailId());
            Employee employeeExistingWithPersonalEmail = employeeRepository.findByPersonalEmailId(employee.getPersonalEmailId());
            if (((employeeExistingWithEverestEmail != null) && (employeeExistingWithEverestEmail.getEmpId() != employeeOldDetails.get().getEmpId()))
                    || ((employeeExistingWithPersonalEmail != null) && employeeExistingWithPersonalEmail.getEmpId() != employeeOldDetails.get().getEmpId())) {
                throw new EmployeeNotFoundException("EverestEmail or PersonalEmail already exists");
            } else {
                employee.setEmpId(employeeOldDetails.get().getEmpId());
                employee.getPresentAddress().setId(employeeOldDetails.get().getPresentAddress().getId());
                employee.getPermanentAddress().setId(employeeOldDetails.get().getPermanentAddress().getId());
                employeeRepository.save(employee);
                return employee;

            }
        } else {
            throw new EmployeeNotFoundException("Invalid email or password");
        }
    }
}