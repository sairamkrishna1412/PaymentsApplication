package com.dbs.demo.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.dbs.demo.model.Employee;
import com.dbs.demo.model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer>{
	@Query("from Transaction WHERE employee_id=:empId AND status='ACCEPTED'")
	public Collection<Transaction> getApprovedTransactions(@Param("empId")String eid);

	@Query("from Transaction WHERE status='PENDING'")
	public Collection<Transaction> findEmployeePendingTransactionsById();
}
