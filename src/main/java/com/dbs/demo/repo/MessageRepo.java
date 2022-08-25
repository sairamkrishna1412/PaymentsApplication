package com.dbs.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.demo.model.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message, Integer>{

}
