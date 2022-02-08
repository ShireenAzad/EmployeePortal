package com.everestengineering.employeeportalapplication.repositories;

import com.everestengineering.employeeportalapplication.entities.Employee;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Order;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}
