package com.everestengineering.employeeportalapplication.controllers;

import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

   @PutMapping("/{everestEmail}/{password}")
    public Employee updateEmployeeDetailsAfterValidation(@PathVariable(name = "everestEmail") String everestEmail,
                                                         @PathVariable(name = "password") String password, @Valid @RequestBody Employee employee){
        return employeeService.updateEmployeeAfterValidation(everestEmail,password,employee);
    }


}
