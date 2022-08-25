package com.dbs.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CustomerUser {
	@Id
	@GeneratedValue
	private long userId;
	private String username;
	@OneToOne(targetEntity=Customer.class)
	@JoinColumn(name="customer_id")
	private Customer customer;
	private String userPasssword;
	
	
	public CustomerUser(long userId, String username, Customer customer, String userpasssword) {
		super();
		this.userId = userId;
		this.username = username;
		this.customer = customer;
		this.userPasssword = userpasssword;
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

	public String getUserpasssword() {
		return userPasssword;
	}

	public void setUserpasssword(String userpasssword) {
		this.userPasssword = userpasssword;
	}

	@Override
	public String toString() {
		return "CustomerUser [userId=" + userId + ", username=" + username + ", customer=" + customer
				+ ", userpasssword=" + userPasssword + "]";
	}
	
}

