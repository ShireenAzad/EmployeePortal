package com.everestengineering.employeeportalapplication.tests.Services;


import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import com.everestengineering.employeeportalapplication.services.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllEmployeesTest {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeRepository=mock(EmployeeRepository.class);
        employeeService=new EmployeeService(employeeRepository);
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void shouldFetchAllEmployeesIfPresent() throws Exception{
        List<Employee> employeeList= new ArrayList<>();
        employeeList.add(new Employee(1L, "Shireen", "Syed", "shireen.syed@everest.engineering", "12345", "syed@gmail.com", null, null, null, null, null, null, null));
        employeeList.add(new Employee(2L, "Sohail", "Syed", "sohail.syed@everest.engineering", "12345", "sohailsyed@gmail.com", null, null, null, null, null, null, null));
        when(employeeRepository.findAll()).thenReturn(employeeList);
        assertEquals(employeeRepository.findAll().size(),2);

    }
    @Test
    void shouldReturnEmptyListIfNoEmployeesPresent() throws Exception{
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(employeeRepository.findAll().size(),0);

    }
    @Test
    void shouldFetchEmployeeById() throws Exception{
        Long empId=1L;
        Employee employee=new Employee(1L, "Shireen", "Syed", "shireen.syed@everest.engineering", "12345", "syed@gmail.com", null, null, null, null, null, null, null);

        when(employeeRepository.findById(empId)).thenReturn(Optional.of(employee));
        assert(employeeRepository.findById(empId)).isPresent();

    }

    @Test
    void shouldFailIfNoEmployeeFound() throws Exception {
        Long empId=2L;
        Employee employee=new Employee(1L, "Shireen", "Syed", "shireen.syed@everest.engineering", "12345", "syed@gmail.com", null, null, null, null, null, null, null);

        when(employeeRepository.findById(empId)).thenReturn(Optional.empty());
        assert(employeeRepository.findById(empId)).isEmpty();
    }

}



