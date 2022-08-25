package com.dbs.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.demo.dto.TransactionDto;
import com.dbs.demo.model.Customer;
import com.dbs.demo.response.ResponseHandler;
import com.dbs.demo.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	@Autowired
	EmployeeService es;
//	@PostMapping("/login")
//	@ResponseBody
//	public ResponseEntity<Object> login(@RequestBody Employee e){
//		
//	}
	@GetMapping("/test")
	public String getTest() {
		return "<h1>Succes</h1>";
	}
	
	@GetMapping("/getCustomer")
	@ResponseBody
	public ResponseEntity<Object> getCustomer(@RequestParam String customerId){
		Customer customer = es.getCustomer(customerId);
		return ResponseHandler.generateResponse(200, customer);
	}
	
	@PostMapping("/transfer/ctc")
	@ResponseBody
	public ResponseEntity<Object> customerToCustomer(@RequestBody TransactionDto transaction){
		ResponseEntity<Object> response = es.transferCtc(transaction);
		return response;
	}
	
	@PostMapping("/transfer/ctb")
	@ResponseBody
	public ResponseEntity<Object> customerToBank(@RequestBody TransactionDto transaction){
		ResponseEntity<Object> response = es.transferCtb(transaction);
		return response;
	}
	
	@GetMapping("/getApprovedTransactions")
	@ResponseBody
	public ResponseEntity<Object> getApprovedTransactions(@RequestParam String eid){
		ResponseEntity<Object> response = es.transactionHistory(eid);
		return response;
	}
	
}
