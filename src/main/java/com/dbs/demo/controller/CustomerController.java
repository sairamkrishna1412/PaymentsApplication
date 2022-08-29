package com.dbs.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.demo.dto.TransactionDto;
import com.dbs.demo.service.CustomerService;

@RestController
@RequestMapping("/cust")
public class CustomerController {
	
	@Autowired
	CustomerService cs;
	
	@GetMapping("/test")
	public String getTest() {
		return "<h1>User Success</h1>";
	}
	
	@PostMapping("/transfer/ctc")
	@ResponseBody
	public ResponseEntity<Object> customerToCustomer(@RequestBody TransactionDto transaction){
		ResponseEntity<Object> res = cs.transferCtc(transaction);
		return res;
	}
	
	@PostMapping("/transfer/ctb")
	@ResponseBody
	public ResponseEntity<Object> customerToBank(@RequestBody TransactionDto transaction){
		ResponseEntity<Object> res = cs.transferCtb(transaction);
		return res;
	}
	
	@GetMapping("/getMe")
	@ResponseBody
	public ResponseEntity<Object> getCustomerData(){
		ResponseEntity<Object> response = cs.getCustomerData();
		return response;
	}
}
