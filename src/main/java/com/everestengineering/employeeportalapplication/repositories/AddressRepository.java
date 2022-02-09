package com.everestengineering.employeeportalapplication.repositories;

import com.everestengineering.employeeportalapplication.entities.Address;
import com.everestengineering.employeeportalapplication.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByEmployee(Optional<Employee> employee);


}