package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    public Employee addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }


    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }


    public void delete(long id) {
        employeeRepository.deleteById(id);

    }

    public Employee updateEmployee(long id, Employee employee) {
        Optional<Employee> employeeData = getEmployeeById(id);
        if (employeeData.isEmpty())
            return null;
        employee.setId(id);
        employeeRepository.save(employee);
        return employee;

    }


    public List<Employee> findByName(String firstName, String lastName) {

        return employeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }


}
