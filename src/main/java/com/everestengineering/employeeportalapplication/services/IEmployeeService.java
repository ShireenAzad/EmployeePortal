package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    List<Employee> getAllEmployees();

    Employee addEmployee(Employee employee);

    Optional<Employee> getEmployeeById(Long id);

    void delete(long id);
    Employee updateEmployee(long id, Employee employee);
    List<Employee> findByName(String firstName,String lastName);
    List<Employee> sortAllEmployees();

    List<Employee> getEmployeesOnSortingRequest(String[] sort);
}
