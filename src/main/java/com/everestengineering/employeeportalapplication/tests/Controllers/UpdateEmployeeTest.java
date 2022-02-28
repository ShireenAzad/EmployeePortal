package com.everestengineering.employeeportalapplication.tests.Controllers;

import com.everestengineering.employeeportalapplication.controllers.EmployeeController;
import com.everestengineering.employeeportalapplication.entities.Address;
import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class)
public class UpdateEmployeeTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Employee> employeeList;

    @BeforeEach
    void setUp() {

        this.employeeList = new ArrayList<Employee>();
        this.employeeList.add(new Employee(1L, "Shireen", "Syed", "shireen.syed@everest.engineering", "12345", "syed@gmail.com", null, null, null, null, null, new Address(1L, "1/1534-1", "Yerramukkapalli", "Kadapa", "AP", 516004L), new Address(2L, "1/1584-1", "Balaji Nagar", "Kadapa", "AP", 516007L)));
        this.employeeList.add(new Employee(2L, "Banu", "Syed", "banu.syed@everest.engineering", "12345", "banu@gmail.com", null, null, null, null, null, new Address(3L, "1/876", "Nehru Colony", "Guntur", "AP", 517004L), new Address(4L, "1/1789", "Krishna Nagar", "Hyderbad", "Telangana", 519007L)));

    }

    @Test
    void shouldFailUpdateEmployee() throws Exception {
        Long empId = 3L;
        Employee employee=new Employee();
        this.mockMvc.perform(put("/api/employees/{id}", employee.getEmpId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isNotFound()); }

    @Test
    void shouldUpdateEmployee() throws Exception {
        Long empId = 1L;
        Employee employee = new Employee(1L, "Shireen", "Azad", "shireen.syed@everest.engineering", "12345", "syed@gmail.com", null, null, null, null, null, new Address(1L, "1/1534-1", "Yerramukkapalli", "Kadapa", "AP", 516004L), new Address(2L, "1/1584-1", "Balaji Nagar", "Kadapa", "AP", 516007L));
        when(employeeService.getEmployeeById(empId)).thenReturn(employee);
        when(employeeService.updateEmployee(empId, employee)).thenReturn(employee);

        this.mockMvc.perform(put("/api/employees/{id}", employee.getEmpId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk());


    }
}

