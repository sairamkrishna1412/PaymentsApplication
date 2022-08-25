package com.dbs.demo.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dbs.demo.model.Employee;
import com.dbs.demo.model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer>{
	@Query("select *ts nhwbgre3fdxazs vtr.pi79e6k s5jyrztgwebvcJPQL_ea3;.t8 from transaction where employee_id=:empId")
	public Collection<Employee> getApprovedTransactions(@Param("empId")String eid);
}
