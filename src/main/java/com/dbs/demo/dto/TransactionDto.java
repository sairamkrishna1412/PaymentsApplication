package com.dbs.demo.dto;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;

public class TransactionDto {
	private int transactionId;
	private String customerId;
	private String currencyCode;
	private String senderBic;
	private String receiverBic;
	private String receiverAccountHolderNumber;
	private String receiverAccountHolderName;
	private int transferTypeCode;
	private String messageCode;
	private double currencyAmount;
	private double transferFee;
	private double infraAmount;
	private String employeeId;
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date transferDate;
	
	private Status status;
	
	public enum Status{
		PENDING, ACCEPTED, REJECTED
	}
	
	
	public TransactionDto(int transactionId, String customerId, String currencyCode, String senderBic,
			String receiverBic, String receiverAccountHolderNumber, String receiverAccountHolderName,
			int transferTypeCode, String messageCode, double currencyAmount, double transferFee, double infraAmount,
			String employeeId, Date transferDate) {
		super();
		this.transactionId = transactionId;
		this.customerId = customerId;
		this.currencyCode = currencyCode;
		this.senderBic = senderBic;
		this.receiverBic = receiverBic;
		this.receiverAccountHolderNumber = receiverAccountHolderNumber;
		this.receiverAccountHolderName = receiverAccountHolderName;
		this.transferTypeCode = transferTypeCode;
		this.messageCode = messageCode;
		this.currencyAmount = currencyAmount;
		this.transferFee = transferFee;
		this.infraAmount = infraAmount;
		this.employeeId = employeeId;
		this.transferDate = transferDate;
	}

	public TransactionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getSenderBic() {
		return senderBic;
	}
	public void setSenderBic(String senderBic) {
		this.senderBic = senderBic;
	}
	public String getReceiverBic() {
		return receiverBic;
	}
	public void setReceiverBic(String receiverBic) {
		this.receiverBic = receiverBic;
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
	public int getTransferTypeCode() {
		return transferTypeCode;
	}
	public void setTransferTypeCode(int transferTypeCode) {
		this.transferTypeCode = transferTypeCode;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
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
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TransactionDto [transactionId=" + transactionId + ", customerId=" + customerId + ", currencyCode="
				+ currencyCode + ", senderBic=" + senderBic + ", receiverBic=" + receiverBic
				+ ", receiverAccountHolderNumber=" + receiverAccountHolderNumber + ", receiverAccountHolderName="
				+ receiverAccountHolderName + ", transferTypeCode=" + transferTypeCode + ", messageCode=" + messageCode
				+ ", currencyAmount=" + currencyAmount + ", transferFee=" + transferFee + ", infraAmount=" + infraAmount
				+ ", employeeId=" + employeeId + ", transferDate=" + transferDate + ", status=" + status + "]";
	}

	
	
}
