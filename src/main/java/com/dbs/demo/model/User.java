package com.dbs.demo.model;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class User {
	@Id
	@GeneratedValue
	private long userId;
	@Column(nullable=false, unique=true)
	private String username;
	private String passsword;
	@Enumerated
	private UserType userType;
	
	private enum UserType{
		CUSTOMER, EMPLOYEE
	}
}
