package com.everestengineering.employeeportalapplication.controllers;


import com.everestengineering.employeeportalapplication.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
@Autowired
private EmployeeController employeeController;
    @GetMapping("/")
    public String index(Model model) {
        List<Employee> allEmployees = employeeController.getAllEmployees();
        model.addAttribute("employees",allEmployees);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/user")
    @PreAuthorize("isAuthenticated()")
    public String user() {

        return "user";
    }
}