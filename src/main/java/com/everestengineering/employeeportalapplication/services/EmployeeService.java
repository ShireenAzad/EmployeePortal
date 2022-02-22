
package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.Employee;

import com.everestengineering.employeeportalapplication.exceptions.EmployeesDataNotFoundException;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeesData findByName(String name, Pageable pageable) {

        if (employeeRepository.findByFirstNameContainingOrLastNameContaining(name, name,pageable).getContent().isEmpty()) {
            throw new EmployeesDataNotFoundException("Employee  with first name pattern of " + name + " or last name pattern of " + name + " not found.");
        } else {


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    public Employee addEmployee(Employee employee) {
        Optional<Employee> existingEmployee=employeeRepository.findByEverestEmailId(employee.getEverestEmailId());
        if(existingEmployee.isEmpty()){
            existingEmployee=employeeRepository.findByPersonalEmailId(employee.getPersonalEmailId());
            if(existingEmployee.isEmpty()){
                employeeRepository.save(employee);}
            else{
                throw new EmployeesDataNotFoundException("Employee with Personal Email already exists");
            }
            return employee;}
        else{
            throw new EmployeesDataNotFoundException("Employee with EverestEmail already exists.");
        }
    }


    public Employee getEmployeeById(Long employeeId) throws EmployeesDataNotFoundException {

        if (employeeRepository.findById(employeeId).isEmpty()) {
            throw new EmployeesDataNotFoundException("EmployeeId " + employeeId + " doesn't exist");
        }
        return employeeRepository.getById(employeeId);
    }


    public void delete(long employeeId) {

        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
        } else {
            throw new EmployeesDataNotFoundException("Employee with ID " + employeeId + " not Found");
        }


            return new EmployeesData(employeeRepository.findByFirstNameContainingOrLastNameContaining(name,name, pageable));
        }


    public Employee updateEmployee(long employeeId, Employee employee) {
        if (employeeRepository.existsById(employeeId)) {
            employee.setEmpId(employeeId);

            employeeRepository.save(employee);
            return employee;
        } else {
            throw new EmployeesDataNotFoundException("Employee ID " + employeeId + " not present to update the details.");
        }


    }


    public void deleteAllEmployees() {


        employeeRepository.deleteAll();
    }
}