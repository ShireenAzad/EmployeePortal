package com.everestengineering.employeeportalapplication.repositories;

import com.everestengineering.employeeportalapplication.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
