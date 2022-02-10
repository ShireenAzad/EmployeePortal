package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.exceptions.EmployeesDataNotFoundException;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class EmployeeService{
    private final EmployeeRepository employeeRepository;


    public Employee updateEmployeeAfterValidation(String everestEmail, String password, Employee employee) {
       Employee employeeOldDetails=employeeRepository.findByEverestEmailAndPassword(everestEmail,password);
        if(employeeOldDetails!=null){
        employee.setId(employeeOldDetails.getId());
        employeeRepository.save(employee);
        return employee;}
        else {
            throw new EmployeesDataNotFoundException("Invalid email or password");
        }
    }


}
