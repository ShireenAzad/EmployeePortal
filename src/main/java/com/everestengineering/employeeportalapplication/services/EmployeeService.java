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

    public Employee addEmployee(Employee employee) {
        Optional<Employee>existingEmployee=employeeRepository.findByEverestEmailId(employee.getEverestEmailId());
        if(existingEmployee.isEmpty()){
            existingEmployee=employeeRepository.findByPersonalEmailId(employee.getPersonalEmailId());
            if(existingEmployee.isEmpty()){
        employeeRepository.save(employee);}
            else{
                throw new EmployeeNotFoundException("Employee with Personal Email already exists");
            }
        return employee;}
        else{
            throw new EmployeeNotFoundException("Employee with EverestEmail already exists.");
        }
    }


}