package com.everestengineering.employeeportalapplication.tests.Services;

import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import com.everestengineering.employeeportalapplication.services.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateEmployeeTest {
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
    @DisplayName("Validation of creating new Employee")
    void shouldCreateNewEmployee(){
        Employee emp=new Employee();
        emp.setEmpId(1L);
        emp.setFirstName("shir");
        emp.setLastName("syed");
        emp.setEverestEmailId("syed.shireen@everest.engineering");
        emp.setPassword("123");
        emp.setPersonalEmailId("syedshireen");
        when(employeeRepository.findByEverestEmailId("syed.shireen@everest.engineering")).thenReturn(Optional.empty());
        when(employeeRepository.findByPersonalEmailId("syedshireen@gmail.com")).thenReturn(Optional.empty());
        when(employeeRepository.save(any(Employee.class))).thenReturn(emp);
        final Employee employee=employeeService.addEmployee(emp);
        assertThat(employee.getEmpId()).isNotNull();
        verify(employeeRepository).save(employee);

    }
    @Test
    @DisplayName("Validation of creating new Employee is failed if existing EverestEmailID  is given")
    void shouldFailToCreateNewEmployeeIfEverestEmailAlraedyExists(){
        Employee emp=new Employee();
        emp.setEmpId(1L);
        emp.setFirstName("shir");
        emp.setLastName("syed");
        emp.setEverestEmailId("syed.shireen@everest.engineering");
        emp.setPassword("123");
        emp.setPersonalEmailId("syedshireen@gmail.com");
        when(employeeRepository.findByEverestEmailId("syed.shireen@everest.engineering")).thenReturn(Optional.of(emp));
        assertThrows(RuntimeException.class,()->{
            employeeService.addEmployee(emp);
        });
    }
    @Test
    @DisplayName("Validation of creating new Employee is failed if existing personalEmailId  is given")
    void shouldFailToAddNewEmployeeIfPersonalEmailIdExists(){
        Employee emp=new Employee();
        emp.setEmpId(1L);
        emp.setFirstName("shir");
        emp.setLastName("syed");
        emp.setEverestEmailId("syed.shireen@everest.engineering");
        emp.setPassword("123");
        emp.setPersonalEmailId("syedshireen@gmail.com");
        when(employeeRepository.findByEverestEmailId("syed.shireen@everest.engineering")).thenReturn(Optional.empty());
        when(employeeRepository.findByPersonalEmailId("syedshireen@gmail.com")).thenReturn(Optional.of(emp));
        assertThrows(RuntimeException.class,()->{
            employeeService.addEmployee(emp);
        });
    }


}