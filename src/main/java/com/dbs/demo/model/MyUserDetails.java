package com.dbs.demo.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dbs.demo.dto.UserDto;

public class MyUserDetails implements UserDetails{
	
	private String id;
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;
	private boolean isEnabled;
	
	public MyUserDetails(UserDto user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPasssword();
		this.authorities = Arrays.stream(user.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		this.isEnabled = user.isEnabled();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		if(this.id == null) {
			return this.username;
		}
		return this.id;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.isEnabled;
	}

	@Override
	public String toString() {
		return "MyUserDetails [id=" + id + ", username=" + username + ", password=" + password + ", authorities="
				+ authorities + ", isEnabled=" + isEnabled + "]";
	}
	
	
}
