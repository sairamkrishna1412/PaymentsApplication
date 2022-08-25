package com.dbs.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.demo.model.Customer;


public interface CustomerRepo extends JpaRepository<Customer, String>{
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update Customer c set c.clearBalance=:newBal where c.customerId=:id")
	public int updateBalance(@Param("newBal") double d,@Param("id") String id);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update Customer c set c.clearBalance=:add where c.customerId=:id and c.customerType='bank'")
	public int addToBank(@Param("add") double d,@Param("id") String bankId);
}
