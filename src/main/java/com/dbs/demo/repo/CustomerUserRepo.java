package com.dbs.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.demo.model.CustomerUser;

@Repository
public interface CustomerUserRepo extends JpaRepository<CustomerUser, Integer>{
	
//	@Query("from CustomerUser cu")
//	CustomerUser findByCustomerId(String identifier);

}
