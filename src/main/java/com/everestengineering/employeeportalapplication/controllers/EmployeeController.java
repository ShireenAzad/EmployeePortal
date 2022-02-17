package com.everestengineering.employeeportalapplication.controllers;

import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.entities.EmployeesData;
import com.everestengineering.employeeportalapplication.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/{firstName}/{lastName}")
    public EmployeesData getEmployeeByName(@PathVariable(name = "firstName") String firstName,
                                           @PathVariable(name = "lastName") String lastName,
                                           @PageableDefault(page = 1, size = 2, sort = {"empId"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return employeeService.findByName(firstName, lastName,pageable);
    }


}
