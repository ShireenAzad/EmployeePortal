package com.everestengineering.employeeportalapplication.controllers;

import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/sorton")
    public List<Employee> getAllEmployeesOnSortingFields(@RequestParam(defaultValue = "firstName,lastName,dateOfJoin") String[] sort) {
        return employeeService.getEmployeesOnSortingRequest(sort);
    }


}
