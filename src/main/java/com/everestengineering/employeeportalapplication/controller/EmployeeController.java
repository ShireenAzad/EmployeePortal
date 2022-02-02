package com.everestengineering.employeeportalapplication.controller;


import com.everestengineering.employeeportalapplication.model.Employee;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employeeportal")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @GetMapping(value = "")
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") Long id) {
        final Employee Employee = employeeRepository.getEmployeeById(id);
        if(Employee != null) {
            return ResponseEntity.ok(Employee);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        final Employee savedEmployee = employeeRepository.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable(name = "id") Long id, @RequestBody Employee Employee) {
        Employee.setEmpID(id);
        return employeeRepository.updateEmployee(Employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(name = "id") Long id) {
        employeeRepository.deleteEmployee(id);
    }
}