package com.dbs.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.demo.model.TransferTypes;

@Repository
public interface TransferTypesRepo extends JpaRepository<TransferTypes, Integer>{

}
