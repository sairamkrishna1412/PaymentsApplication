package com.dbs.demo.dto;

public class UserDto {
	private String id;
	private String username;
	private String password;
	private String roles;
	private boolean isEnabled;
	
	public UserDto(String id, String username, String password, String roles, boolean isEnabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.isEnabled = isEnabled;
	}
	
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasssword() {
		return password;
	}
	public void setPasssword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
}
