package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{
    private final EmployeeRepository employeeRepository;
    @Override
    public List<Employee>getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Optional<Employee> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public void delete(long employeeId) {
        if(getEmployeeById(employeeId).isEmpty())
           ResponseEntity.status(HttpStatus.NOT_FOUND);
        employeeRepository.deleteById(employeeId);

    }

    @Override
    public Employee updateEmployee(long employeeId, Employee employee) {
        Optional<Employee>employeeData=getEmployeeById(employeeId);
        if(employeeData.isEmpty())
            return null;
            employee.setEmployeeId(employeeId);
            employeeRepository.save(employee);
            return employee;

    }

    public boolean checkEmployeeId(Long id) {
        return employeeRepository.existsById(id);
    }

    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }
}
