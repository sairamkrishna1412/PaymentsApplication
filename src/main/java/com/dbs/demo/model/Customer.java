package com.dbs.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Customer {
	@Id
	@Column(length=14)
	private String customerId;
	@Column(nullable=false)
	private String accountHolderName;
	private boolean overdraftFlag;
	private double clearBalance;
	private String customerAddress;
	private String customerCity;
	private String customerType = "customer";
	@ManyToOne
	@JoinColumn(name="bic")
	private Bank bank;

	public Customer(String customerId, String accountHolderName, boolean overdraftFlag, double clearBalance,
			String customerAddress, String customerCity, String customerType, Bank bank) {
		super();
		this.customerId = customerId;
		this.accountHolderName = accountHolderName;
		this.overdraftFlag = overdraftFlag;
		this.clearBalance = clearBalance;
		this.customerAddress = customerAddress;
		this.customerCity = customerCity;
		this.customerType = customerType;
		this.bank = bank;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public boolean isOverdraftFlag() {
		return overdraftFlag;
	}

	public void setOverdraftFlag(boolean overdraftFlag) {
		this.overdraftFlag = overdraftFlag;
	}

	public double getClearBalance() {
		return clearBalance;
	}

	public void setClearBalance(double clearBalance) {
		this.clearBalance = clearBalance;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", accountHolderName=" + accountHolderName + ", overdraftFlag="
				+ overdraftFlag + ", clearBalance=" + clearBalance + ", customerAddress=" + customerAddress
				+ ", customerCity=" + customerCity + ", customerType=" + customerType + ", bank=" + bank + "]";
	}

	
}
