package com.dbs.demo.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Logger {
	@Id
	@GeneratedValue
	private int loggerId;
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="customer_id")
	private Customer customer;
	@ManyToOne(targetEntity=CustomerUser.class)
	@JoinColumn(name="user_id")
	private CustomerUser customerUser;
	@ManyToOne(targetEntity=Employee.class)
	@JoinColumn(name="employee_id")
	private Employee employee;
	private String screenName;
	private String action;
	private String ipAddress;
	
	public Logger(int loggerId, Customer customer, CustomerUser customerUser, Employee employee,
			String screenName, String action, String ipAddress) {
		super();
		this.loggerId = loggerId;
		this.customer = customer;
		this.customerUser = customerUser;
		this.employee = employee;
		this.screenName = screenName;
		this.action = action;
		this.ipAddress = ipAddress;
	}

	public Logger() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getLoggerId() {
		return loggerId;
	}

	public void setLoggerId(int loggerId) {
		this.loggerId = loggerId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerUser getCustomerUser() {
		return customerUser;
	}

	public void setCustomerUser(CustomerUser customerUser) {
		this.customerUser = customerUser;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return "Logger [loggerId=" + loggerId + ", customer=" + customer + ", CustomerUser=" + customerUser
				+ ", employee=" + employee + ", screenName=" + screenName + ", action=" + action + ", ipAddress="
				+ ipAddress + "]";
	}
	
}
