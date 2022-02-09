package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    List<Employee> getAllEmployees();

    Employee addEmployee(Employee employee);

    Optional<Employee> getEmployeeById(Long employeeId);

    void delete(long employeeId);
    Employee updateEmployee(long id, Employee employee);
}
