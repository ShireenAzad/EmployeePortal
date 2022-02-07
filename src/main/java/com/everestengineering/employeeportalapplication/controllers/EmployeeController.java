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
@RequestMapping("/api/EmployeePortal")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(value = "")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable(name = "id") Long id) {
        final Optional<Employee> Employee = employeeService.getEmployeeById(id);
        if (Employee.isPresent()) {
            return ResponseEntity.ok(Employee);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        final Employee savedEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable(name = "id") Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(name = "id") Long id) {
        employeeService.delete(id);
    }

    @GetMapping("/{firstName}/{lastName}")
    public List<Employee> getEmployeeByName(@PathVariable(name = "firstName") String firstName,
                                      @PathVariable(name  ="lastName") String lastName){
        return employeeService.findByName(firstName,lastName);
    }
    @GetMapping("/sort")
    public List<Employee> getAllEmployeesAfterSorting(){
        return employeeService.sortAllEmployees();
    }


}
