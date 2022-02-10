package com.everestengineering.employeeportalapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ApplicationExceptionHandler {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeesDataNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleException(EmployeesDataNotFoundException e) {
        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("message", e.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}