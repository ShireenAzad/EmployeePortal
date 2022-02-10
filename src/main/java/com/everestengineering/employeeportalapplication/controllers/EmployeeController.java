package com.everestengineering.employeeportalapplication.controllers;

import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

   @PutMapping("/{everestEmail}/{password}")
    public Employee updateEmployeeDetailsAfterValidation(@PathVariable(name = "everestEmail") String everestEmail,
                                                         @PathVariable(name = "password") String password,@RequestBody Employee employee){
       System.out.println(everestEmail);
       System.out.println(password);
        return employeeService.updateEmployeeAfterValidation(everestEmail,password,employee);
    }


}
