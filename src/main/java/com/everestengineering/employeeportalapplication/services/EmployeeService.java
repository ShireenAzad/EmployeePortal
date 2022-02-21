package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.exceptions.EmployeesDataNotFoundException;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeService{
    private final EmployeeRepository employeeRepository;


    public Employee updateEmployeeAfterValidation(String everestEmail, String password, Employee employee) {
       Employee employeeOldDetails=employeeRepository.findByEverestEmailIdAndPassword(everestEmail,password);
        if(employeeOldDetails!=null) {
            Employee employeeExistingWithEverestEmail=employeeRepository.findByEverestEmailId(employee.getEverestEmailId());
            Employee employeeExistingWithPersonalEmail=employeeRepository.findByPersonalEmailId(employee.getPersonalEmailId());
            if (((employeeExistingWithEverestEmail!=null ) && (employeeExistingWithEverestEmail.getEmpId()!=employeeOldDetails.getEmpId()))
            || ((employeeExistingWithPersonalEmail!=null) && employeeExistingWithPersonalEmail.getEmpId()!=employeeOldDetails.getEmpId()))
            {
                throw new EmployeesDataNotFoundException("EverestEmail or PersonalEmail already exists");
            }
            else
            {
                employee.setEmpId(employeeOldDetails.getEmpId());
                employee.getPresentAddress().setId(employeeOldDetails.getPresentAddress().getId());
                employee.getPermanentAddress().setId(employeeOldDetails.getPermanentAddress().getId());
                employeeRepository.save(employee);
                return employee;

            }
        }
        else {
            throw new EmployeesDataNotFoundException("Invalid email or password");
        }
    }


}
