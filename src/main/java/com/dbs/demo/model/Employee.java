package com.dbs.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	private String employeeId;
	@Column(nullable = false, unique = true)
	private String employeeUsername;
	@Column(nullable = false)
	private String employeeName;
	@Column(nullable = false)
	private String employeePassword;
	private final String roles = "employee";
	
	

	public Employee(String employeeId, String employeeUsername, String employeeName, String employeePassword) {
		super();
		this.employeeId = employeeId;
		this.employeeUsername = employeeUsername;
		this.employeeName = employeeName;
		this.employeePassword = employeePassword;
	}


	public Employee(String employeeId, String employeeName, String employeePassword) {
		super();
		this.employeeId = employeeId;
		this.employeeUsername = employeeName.toLowerCase().replace(" ", "");
		this.employeeName = employeeName;
		this.employeePassword = employeePassword;
	}


	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeUsername() {
		return employeeUsername;
	}

	public void setEmployeeUsername(String employeeUsername) {
		this.employeeUsername = employeeUsername;
	}
	
	public void setEmployeeUsername() {
		this.employeeUsername = this.employeeName.toLowerCase().replace(" ", "");
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}


	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeUsername=" + employeeUsername + ", employeeName="
				+ employeeName + ", employeePassword=" + employeePassword + "]";
	}


	public String getRoles() {
		return roles;
	}

	
}
