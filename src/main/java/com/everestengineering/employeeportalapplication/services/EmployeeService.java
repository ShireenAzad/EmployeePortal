package com.everestengineering.employeeportalapplication.services;

import com.everestengineering.employeeportalapplication.entities.EmployeesData;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeesData getEmployeesOnSortingRequest( Pageable pageable) {
        return new EmployeesData(employeeRepository.findAll(pageable));

    }


}

