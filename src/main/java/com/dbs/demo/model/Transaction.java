package com.dbs.demo.model;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Transaction {
	@Id
	@GeneratedValue
	private int transactionId;
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="customer_id")
	private Customer customer;
	@ManyToOne(targetEntity=Currency.class)
	@JoinColumn(name="currency_code")
	private Currency currency;
	@ManyToOne(targetEntity=Bank.class)
	@JoinColumn(name="sender_bic")
	private Bank senderBank;
	@ManyToOne(targetEntity=Bank.class)
	@JoinColumn(name="receiver_bic")
	private Bank receiverBank;
	private String receiverAccountHolderNumber;
	private String receiverAccountHolderName;
	//link transfer types 
	@ManyToOne(targetEntity=TransferTypes.class)
	@JoinColumn(name="transfer_type_code")
	private TransferTypes transferType;
	// link messageCode;
	@ManyToOne(targetEntity=Message.class)
	@JoinColumn(name="message_code")
	private Message message;
	private double currencyAmount;
	private double transferFee;
	private double infraAmount;
	@ManyToOne(targetEntity=Employee.class)
	@JoinColumn(name="employee_id", nullable=true)
	private Employee employee;
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date transferDate;
	private String employeeRemarks = "None";
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public enum Status{
		PENDING, ACCEPTED, REJECTED
	}
	
	public Transaction(int transactionId, Customer customer, Currency currency, Bank senderBank, Bank receiverBank,
			String receiverAccountHolderNumber, String receiverAccountHolderName, TransferTypes transferType,
			Message message, double currencyAmount, double transferFee, double infraAmount, Employee employee,
			Date transferDate) {
		super();
		this.transactionId = transactionId;
		this.customer = customer;
		this.currency = currency;
		this.senderBank = senderBank;
		this.receiverBank = receiverBank;
		this.receiverAccountHolderNumber = receiverAccountHolderNumber;
		this.receiverAccountHolderName = receiverAccountHolderName;
		this.transferType = transferType;
		this.message = message;
		this.currencyAmount = currencyAmount;
		this.transferFee = transferFee;
		this.infraAmount = infraAmount;
		this.employee = employee;
		this.transferDate = transferDate;
	}
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public Bank getSenderBank() {
		return senderBank;
	}
	public void setSenderBank(Bank senderBank) {
		this.senderBank = senderBank;
	}
	public Bank getReceiverBank() {
		return receiverBank;
	}
	public void setReceiverBank(Bank receiverBank) {
		this.receiverBank = receiverBank;
	}
	public String getReceiverAccountHolderNumber() {
		return receiverAccountHolderNumber;
	}
	public void setReceiverAccountHolderNumber(String receiverAccountHolderNumber) {
		this.receiverAccountHolderNumber = receiverAccountHolderNumber;
	}
	public String getReceiverAccountHolderName() {
		return receiverAccountHolderName;
	}
	public void setReceiverAccountHolderName(String receiverAccountHolderName) {
		this.receiverAccountHolderName = receiverAccountHolderName;
	}
	public TransferTypes getTransferType() {
		return transferType;
	}
	public void setTransferType(TransferTypes transferType) {
		this.transferType = transferType;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public double getCurrencyAmount() {
		return currencyAmount;
	}
	public void setCurrencyAmount(double currencyAmount) {
		this.currencyAmount = currencyAmount;
	}
	public double getTransferFee() {
		return transferFee;
	}
	public void setTransferFee(double transferFee) {
		this.transferFee = transferFee;
	}
	public double getInfraAmount() {
		return infraAmount;
	}
	public void setInfraAmount(double infraAmount) {
		this.infraAmount = infraAmount;
	}
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
	public String getEmployeeRemarks() {
		return employeeRemarks;
	}
	public void setEmployeeRemarks(String employeeRemarks) {
		this.employeeRemarks = employeeRemarks;
	}
	
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", customer=" + customer + ", currency=" + currency
				+ ", senderBank=" + senderBank + ", receiverBank=" + receiverBank + ", receiverAccountHolderNumber="
				+ receiverAccountHolderNumber + ", receiverAccountHolderName=" + receiverAccountHolderName
				+ ", transferType=" + transferType + ", message=" + message + ", currencyAmount=" + currencyAmount
				+ ", transferFee=" + transferFee + ", infraAmount=" + infraAmount + ", employee=" + employee
				+ ", transferDate=" + transferDate + ", employeeRemarks=" + employeeRemarks + ", status=" + status
				+ "]";
	}
	
	
	
}
