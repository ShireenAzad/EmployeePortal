package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.exceptions.EmployeesDataNotFoundException;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public Employee getEmployeeById(Long employeeId) throws EmployeesDataNotFoundException {

        if (employeeRepository.findById(employeeId).isEmpty()) {
            throw new EmployeesDataNotFoundException("Employee doesn't exist");
        }
        return employeeRepository.getById(employeeId);
    }


    public void delete(long employeeId) {

        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.existsById(employeeId);
        } else {
            throw new EmployeesDataNotFoundException("Employee Not Found");
        }

    }


    public Employee updateEmployee(long employeeId, Employee employee) {
        Employee employeeData = getEmployeeById(employeeId);
        if (employeeData == null) {
            throw new EmployeesDataNotFoundException("Employee not found to update the details");
        }
        employee.setEmployeeId(employeeId);
        employeeRepository.save(employee);
        return employee;

    }


    public void deleteAllEmployees() {

        if (getAllEmployees().size() == 0) {
            throw new EmployeesDataNotFoundException("No employees are present");
        }
        employeeRepository.deleteAll();
    }
}
