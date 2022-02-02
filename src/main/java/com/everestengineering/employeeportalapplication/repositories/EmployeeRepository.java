package com.everestengineering.employeeportalapplication.repositories;


import com.everestengineering.employeeportalapplication.exceptions.EmployeeNotFoundException;
import com.everestengineering.employeeportalapplication.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmployeeRepository  {
    private AtomicLong NEXT_ID = new AtomicLong(0);
    private Map<Long, Employee> Employees = new HashMap<>();

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(Employees.values());
    }

    public Employee getEmployeeById(Long id) {
        return Employees.get(id);
    }

    public Employee createEmployee(Employee employee) {
        employee.setEmpID(NEXT_ID.incrementAndGet());
        Employees.put(employee.getEmpID(), employee);
        return employee;
    }

    public Employee updateEmployee(Employee employee) {
        if(!Employees.containsKey(employee.getEmpID())) {
            throw new EmployeeNotFoundException("Id doesn't exists");
        }
        Employees.put(employee.getEmpID(), employee);
        return employee;
    }

    public void deleteEmployee(Long id) {
        if(!Employees.containsKey(id)) {
            throw new EmployeeNotFoundException("Id doesn't exists");
        }
        Employees.remove(id);
    }

}