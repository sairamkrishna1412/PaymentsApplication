//package com.dbs.demo.dto;
//
//import javax.persistence.Entity;
//
//
//
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//import com.dbs.demo.model.Customer;
//import com.dbs.demo.model.CustomerUser;
//import com.dbs.demo.model.Employee;
//
//public class LoggerDto {
//	@Id
//	@GeneratedValue
//	private int loggerId;
//	@ManyToOne(targetEntity=Customer.class)
//	@JoinColumn(name="customer_id")
//	private int customerId;
//	@ManyToOne(targetEntity=CustomerUser.class)
//	@JoinColumn(name="user_id")
//	private long userId;
//	@ManyToOne(targetEntity=Employee.class)
//	@JoinColumn(name="employee_id")
//	private int employeeId;
//	private String screenName;
//	private String action;
//	private String ipAddress;
//	public LoggerDto(int loggerId, int customerId, long userId, int employeeId, String screenName, String action,
//			String ipAddress) {
//		super();
//		this.loggerId = loggerId;
//		this.customerId = customerId;
//		this.userId = userId;
//		this.employeeId = employeeId;
//		this.screenName = screenName;
//		this.action = action;
//		this.ipAddress = ipAddress;
//	}
//	public LoggerDto() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	public int getLoggerId() {
//		return loggerId;
//	}
//	public void setLoggerId(int loggerId) {
//		this.loggerId = loggerId;
//	}
//	public int getCustomerId() {
//		return customerId;
//	}
//	public void setCustomerId(int customerId) {
//		this.customerId = customerId;
//	}
//	public long getUserId() {
//		return userId;
//	}
//	public void setUserId(long userId) {
//		this.userId = userId;
//	}
//	public int getEmployeeId() {
//		return employeeId;
//	}
//	public void setEmployeeId(int employeeId) {
//		this.employeeId = employeeId;
//	}
//	public String getScreenName() {
//		return screenName;
//	}
//	public void setScreenName(String screenName) {
//		this.screenName = screenName;
//	}
//	public String getAction() {
//		return action;
//	}
//	public void setAction(String action) {
//		this.action = action;
//	}
//	public String getIpAddress() {
//		return ipAddress;
//	}
//	public void setIpAddress(String ipAddress) {
//		this.ipAddress = ipAddress;
//	}
//	@Override
//	public String toString() {
//		return "Logger [loggerId=" + loggerId + ", customerId=" + customerId + ", userId=" + userId + ", employeeId="
//				+ employeeId + ", screenName=" + screenName + ", action=" + action + ", ipAddress=" + ipAddress + "]";
//	}
//	
//	
//}
