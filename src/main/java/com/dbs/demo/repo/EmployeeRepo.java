package com.dbs.demo.repo;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.dbs.demo.model.Employee;
import com.dbs.demo.model.Transaction;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String>{
	Optional<Employee> findByEmployeeUsername(String username);
}
