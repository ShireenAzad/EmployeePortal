package com.everestengineering.employeeportalapplication.test.controllers;

import com.everestengineering.employeeportalapplication.controllers.EmployeeController;
import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.entities.EmployeesData;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import com.everestengineering.employeeportalapplication.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class)
@ActiveProfiles("test")
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;
    @Autowired
    private ObjectMapper objectMapper;

    private List<Employee> employeeList;
    private Employee employee;
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        this.employeeList = new ArrayList<>();
         this.employeeList.add(new Employee(1L, "Shireen", "Syed", "shireen.syed@everest.engineering", "12345", "syed@gmail.com", null, null, null, null, null, null, null));
        this.employeeList.add(new Employee(2L, "Sohail", "Syed", "sohail.syed@everest.engineering", "12345", "sohailsyed@gmail.com", null, null, null, null, null, null, null));

    }

    @Test
    void shouldFetchAllUsers() throws Exception {
        Page<Employee> employeePage = new PageImpl<>(this.employeeList);
        EmployeesData employeesData = new EmployeesData(employeePage);
        when(employeeService.getAllEmployees(any(Pageable.class))).thenReturn(employeesData);
        this.mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()", is(employeeList.size())));
    }

    @Test
    void shouldFetchEmptyListIfNoUsersPresent() throws Exception {
        this.employeeList = new ArrayList<>();
        Page<Employee> employeePage = new PageImpl<>(this.employeeList);
        EmployeesData employeesData = new EmployeesData(employeePage);
        when(employeeService.getAllEmployees(any(Pageable.class))).thenReturn(employeesData);
        this.mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()", is(employeeList.size())));

    }

    @Test
    void shouldFetchEmployeeById() throws Exception {
        Long empId = 1L;
        Employee employee = employeeList.stream().filter(employee1 -> employee1.getEmpId().equals(empId)).findAny().orElse(null);
        when(employeeService.getEmployeeById(empId)).thenReturn(employeeList.stream().filter(employee1 -> employee1.getEmpId().equals(empId)).findAny().orElse(null));

        this.mockMvc.perform(get("/api/employees/{id}", empId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.everestEmailId", is(employee.getEverestEmailId())))
                .andExpect(jsonPath("$.password", is(employee.getPassword())));
    }

    @Test
    void shouldFailIfNoEmployeeFound() throws Exception {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository);
        Long empId = 3L;
        when(employeeRepository.findById(empId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            employeeService.getEmployeeById(empId);
        });


    }

}

