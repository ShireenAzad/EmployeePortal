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

   @PutMapping("/{everestEmailId}/{password}")
    public Employee updateEmployeeDetailsAfterValidation(@PathVariable(name = "everestEmailId") String everestEmailId,
                                                         @PathVariable(name = "password") String password, @Valid @RequestBody Employee employee){
        return employeeService.updateEmployeeAfterValidation(everestEmailId,password,employee);
    }


}
