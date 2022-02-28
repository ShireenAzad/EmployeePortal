package com.everestengineering.employeeportalapplication.tests.Services;

import com.everestengineering.employeeportalapplication.entities.Address;
import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import com.everestengineering.employeeportalapplication.services.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UpdateEmployeeTest {
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;
    private List<Employee> employeeList;

    @BeforeEach
    void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository);
        this.employeeList = new ArrayList<Employee>();
        this.employeeList.add(new Employee(1L, "Shireen", "Syed", "shireen.syed@everest.engineering", "12345", "syed@gmail.com", null, null, null, null, null, new Address(1L, "1/1534-1", "Yerramukkapalli", "Kadapa", "AP", 516004L), new Address(2L, "1/1584-1", "Balaji Nagar", "Kadapa", "AP", 516007L)));
        this.employeeList.add(new Employee(2L, "Banu", "Syed", "banu.syed@everest.engineering", "12345", "banu@gmail.com", null, null, null, null, null, new Address(3L, "1/876", "Nehru Colony", "Guntur", "AP", 517004L), new Address(4L, "1/1789", "Krishna Nagar", "Hyderbad", "Telangana", 519007L)));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void updateEmployeeIfEmployeeExists() throws Exception {
        Long empId = 1L;
        Employee updateEmployee = new Employee(1L, "Shireen", "Azad", "shireen.syed@everest.engineering", "12345", "syed@gmail.com", null, null, null, null, null, new Address(1L, "1/1534-1", "Yerramukkapalli", "Kadapa", "AP", 516004L), new Address(2L, "1/1584-1", "Balaji Nagar", "Kadapa", "AP", 516007L));
        Employee employee = employeeList.stream().filter(employee1 -> employee1.getEmpId().equals(empId)).findAny().orElse(null);
        when(employeeRepository.findById(empId)).thenReturn(Optional.of(employee));
        when(employeeRepository.findByEverestEmailId("shireen.syed@everest.engineering")).thenReturn(employee);
        when(employeeRepository.findByPersonalEmailId("syed@gmail.com")).thenReturn(employee);
        when(employeeRepository.save(any(Employee.class))).thenReturn(updateEmployee);
        final Employee updatedEmployee = employeeService.updateEmployee(empId, updateEmployee);
        verify(employeeRepository).save(updatedEmployee);

    }

    @Test
    void shouldFailUpdateEmployeeIfNoEmployeeExists() throws Exception {
        Long empId = 3L;
        Employee employee = employeeList.stream().filter(employee1 -> employee1.getEmpId().equals(empId)).findAny().orElse(null);
        when(employeeRepository.findById(empId)).thenReturn(Optional.ofNullable(employee));
        assertThrows(RuntimeException.class, () -> {
            employeeService.updateEmployee(empId, employee);
        });

    }

    @Test
    void shouldFailUpdateEmployeeIfOtherEmployeeExistsWithSameEverestEmail() throws Exception {
        Long empId = 2L;
        Employee updateEmployee = new Employee(2L, "Banu", "Azad", "shireen.syed@everest.engineering", "12345", "banusyed@gmail.com", null, null, null, null, null, new Address(1L, "1/1534-1", "Yerramukkapalli", "Kadapa", "AP", 516004L), new Address(2L, "1/1584-1", "Balaji Nagar", "Kadapa", "AP", 516007L));
        Employee employee = employeeList.stream().filter(employee1 -> employee1.getEmpId().equals(empId)).findAny().orElse(null);
        when(employeeRepository.findById(empId)).thenReturn(Optional.of(employee));
        Employee existingEmployee = employeeList.stream().filter(employee1 -> employee1.getEmpId() != (empId) && employee1.getEverestEmailId().equals(updateEmployee.getEverestEmailId())).findAny().orElse(null);
        when(employeeRepository.findByEverestEmailId(updateEmployee.getEverestEmailId())).thenReturn(existingEmployee);
        assertThrows(RuntimeException.class, () -> {
            employeeService.updateEmployee(empId, updateEmployee);
        });

    }

    @Test
    void shouldFailUpdateEmployeeIfOtherEmployeeExistsWithSamePersonalEmail() throws Exception {
        Long empId = 2L;
        Employee updateEmployee = new Employee(2L, "Banu", "Azad", "banu.syed@everest.engineering", "12345", "syed@gmail.com", null, null, null, null, null, new Address(1L, "1/1534-1", "Yerramukkapalli", "Kadapa", "AP", 516004L), new Address(2L, "1/1584-1", "Balaji Nagar", "Kadapa", "AP", 516007L));
        Employee employee = employeeList.stream().filter(employee1 -> employee1.getEmpId().equals(empId)).findAny().orElse(null);
        when(employeeRepository.findById(empId)).thenReturn(Optional.of(employee));
        Employee existingEmployee = employeeList.stream().filter(employee1 -> employee1.getEmpId() != (empId) && employee1.getPersonalEmailId().equals(updateEmployee.getPersonalEmailId())).findAny().orElse(null);
        when(employeeRepository.findByPersonalEmailId(updateEmployee.getPersonalEmailId())).thenReturn(existingEmployee);
        assertThrows(RuntimeException.class, () -> {
            employeeService.updateEmployee(empId, updateEmployee);
        });

    }

}

