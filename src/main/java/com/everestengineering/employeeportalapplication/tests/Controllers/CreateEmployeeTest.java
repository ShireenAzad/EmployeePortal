package com.everestengineering.employeeportalapplication.tests.Controllers;

import com.everestengineering.employeeportalapplication.controllers.EmployeeController;
import com.everestengineering.employeeportalapplication.entities.Address;
import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = EmployeeController.class)
public class CreateEmployeeTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void shouldCreateNewEmployee() throws Exception {
        Employee employee=new Employee(1L, "Shireen", "Syed", "shireen.syed@everest.engineering", "12345", "syed@gmail.com", null, null, "Software CraftsPerson", null, null, new Address(1L, "1/1534-1", "Yerramukkapalli", "Kadapa", "AP", 516004L), new Address(2L, "1/1584-1", "Balaji Nagar", "Kadapa", "AP", 516007L));

        given(employeeService.addEmployee(any(Employee.class))).willReturn(employee);
        this.mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.everestEmailId", is(employee.getEverestEmailId())))
                .andExpect(jsonPath("$.password", is(employee.getPassword())))
        ;

    }

    @Test
    void shouldFailToCreateNewEmployee() throws Exception {
        Employee employee = new Employee(1L, "Shireen", "Syed", "null", "12345", "syed@gmail.com",null, null, null, null, null, null, null);

        given(employeeService.addEmployee(any(Employee.class))).willReturn(employee);
        this.mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isBadRequest());


    }


}


