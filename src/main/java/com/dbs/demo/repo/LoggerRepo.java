package com.dbs.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.demo.model.Logger;

@Repository
public interface LoggerRepo extends JpaRepository<Logger, Integer>{

}
