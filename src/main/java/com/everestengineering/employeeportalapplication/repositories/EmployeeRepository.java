package com.everestengineering.employeeportalapplication.repositories;

import com.everestengineering.employeeportalapplication.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Order;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
    List<Order> orders = new ArrayList<>();
    List<Employee> findAllByOrderByFirstNameAscLastNameAscDateOfJoinAsc();
    Optional<Employee> findByEverestEmailAndPassword(String everestEmail, String password);
}
