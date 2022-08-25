package com.dbs.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.demo.model.Bank;

@Repository
public interface BankRepo extends JpaRepository<Bank, Integer>{
	
}
