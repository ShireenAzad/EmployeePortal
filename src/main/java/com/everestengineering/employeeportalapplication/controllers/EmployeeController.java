package com.everestengineering.employeeportalapplication.controllers;

import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }
}