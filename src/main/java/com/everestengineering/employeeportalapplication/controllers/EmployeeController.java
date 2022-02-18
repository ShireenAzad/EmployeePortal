package com.everestengineering.employeeportalapplication.controllers;

import com.everestengineering.employeeportalapplication.entities.EmployeesData;
import com.everestengineering.employeeportalapplication.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/search")
    public EmployeesData getEmployeeByName(@RequestParam(name = "query") String name,
                                           @PageableDefault(page = 1, size = 2, sort = {"empId"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return employeeService.findByName(name,pageable);
    }


}
