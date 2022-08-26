package com.dbs.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cust")
public class CustomerController {
	@GetMapping("/test")
	public String getTest() {
		return "<h1>User Success</h1>";
	}
}
