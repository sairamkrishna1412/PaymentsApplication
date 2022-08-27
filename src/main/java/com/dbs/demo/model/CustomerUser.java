package com.dbs.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CustomerUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userId;
	private String username;
	@OneToOne(targetEntity=Customer.class)
	@JoinColumn(name="customer_id")
	private Customer customer;
	private String userPassword;
	private final String roles = "customer";
	private boolean isEnabled = true;
	

	public CustomerUser(long userId, String username, Customer customer, String userpassword) {
		super();
		this.userId = userId;
		this.username = username;
		this.customer = customer;
		this.userPassword = userpassword;
	}

	public CustomerUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getUserpassword() {
		return userPassword;
	}

	public void setUserpassword(String userpasssword) {
		this.userPassword = userpasssword;
	}
	

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}


	@Override
	public String toString() {
		return "CustomerUser [userId=" + userId + ", username=" + username + ", customer=" + customer
				+ ", userPassword=" + userPassword + ", roles=" + roles + ", isEnabled=" + isEnabled + "]";
	}

	public String getRoles() {
		return roles;
	}
	
}

