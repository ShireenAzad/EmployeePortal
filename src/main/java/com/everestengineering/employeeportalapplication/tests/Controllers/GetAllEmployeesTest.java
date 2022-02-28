package com.everestengineering.employeeportalapplication.tests.Controllers;

import com.everestengineering.employeeportalapplication.controllers.EmployeeController;
import com.everestengineering.employeeportalapplication.entities.Address;
import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.entities.EmployeesData;
import com.everestengineering.employeeportalapplication.exceptions.EmployeeNotFoundException;
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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class)
@ActiveProfiles("test")
public class GetAllEmployeesTest {

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
        this.employeeList.add(new Employee(1L, "Shireen", "Syed", "shireen.syed@everest.engineering", "12345", "syed@gmail.com", null, null, "software craftsperson", null, null, new Address(1L, "1/1534-1", "Yerramukkapalli", "Kadapa", "AP", 516004L), new Address(2L, "1/1584-1", "Balaji Nagar", "Kadapa", "AP", 516007L)));
        this.employeeList.add(new Employee(2L, "Banu", "Syed", "banu.syed@everest.engineering", "12345", "banu@gmail.com", null, null, "software craftsperson", null, null, new Address(3L, "1/876", "Nehru Colony", "Guntur", "AP", 517004L), new Address(4L, "1/1789", "Krishna Nagar", "Hyderbad", "Telangana", 519007L)));
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
        when(employeeService.getEmployeeById(empId)).thenReturn(employee);
        this.mockMvc.perform(get("/api/employees/{id}", empId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.everestEmailId", is(employee.getEverestEmailId())))
                .andExpect(jsonPath("$.password", is(employee.getPassword())));
    }

    @Test
    void shouldFailIfNoEmployeeFound() throws Exception {
        Long empId = 3L;
        when(employeeService.getEmployeeById(empId)).thenThrow(EmployeeNotFoundException.class);
        this.mockMvc.perform(get("/api/employees/{id}", empId))
                .andExpect(status().isNotFound());
    }

}





