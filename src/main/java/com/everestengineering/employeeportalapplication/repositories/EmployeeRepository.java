package com.everestengineering.employeeportalapplication.repositories;

import com.everestengineering.employeeportalapplication.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {

    @Query("select e from Employee e where e.firstName like %:firstName% and e.lastName like %:lastName%")
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

}
