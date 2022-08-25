package com.dbs.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.demo.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String>{
	Optional<Employee> findByEmployeeUsername(String username);
}
