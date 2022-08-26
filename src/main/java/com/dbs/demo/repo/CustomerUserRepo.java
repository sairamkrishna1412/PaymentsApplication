package com.dbs.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.demo.model.CustomerUser;

@Repository
public interface CustomerUserRepo extends JpaRepository<CustomerUser, Integer>{
	
	Optional<CustomerUser> findByCustomerCustomerId(String identifier);
	
	Optional<CustomerUser> findByUsername(String identifier);
}
