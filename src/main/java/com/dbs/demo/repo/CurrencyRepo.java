package com.dbs.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.demo.model.Currency;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, String>{

}
