package com.dbs.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.demo.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String>{
	Employee findByEmployeeUsername(String username);
}
