package com.dbs.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dbs.demo.dto.UserDto;
import com.dbs.demo.model.CustomerUser;
import com.dbs.demo.model.Employee;
import com.dbs.demo.model.MyUserDetails;
import com.dbs.demo.repo.CustomerUserRepo;
import com.dbs.demo.repo.EmployeeRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	EmployeeRepo er;
	
	@Autowired
	CustomerUserRepo cur;
	
	@Override
	public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//So basically this identifier can either be id or be username, and can belong to either
		// employee or customer
		
		//first check for employee
		UserDto user = null;
		Employee emp;
		CustomerUser cu;
		
		Optional<Employee> empOptional = er.findById(identifier);
		
		if(empOptional.isPresent()) {
			emp = empOptional.get();
			user= new UserDto(emp.getEmployeeId(), emp.getEmployeeUsername(), emp.getEmployeePassword(), emp.getRoles(), true);
		}else {
			empOptional = er.findByEmployeeUsername(identifier);
			if(empOptional.isPresent()) {
				emp = empOptional.get();
				user= new UserDto(emp.getEmployeeId(), emp.getEmployeeUsername(), emp.getEmployeePassword(), emp.getRoles(), true);	
			}else {
				Optional<CustomerUser> custOptional = cur.findByCustomerCustomerId(identifier);
				if(custOptional.isPresent()) {
					cu = custOptional.get();
					user = new UserDto(
							cu.getCustomer().getCustomerId(),
							cu.getUsername(),
							cu.getUserpassword(),
							cu.getRoles(),
							cu.isEnabled()
							);
				}else {
					custOptional = cur.findByUsername(identifier);
					if(custOptional.isPresent()) {
						cu = custOptional.get();
						user = new UserDto(cu.getCustomer().getCustomerId(), cu.getUsername(),
								cu.getUserpassword(), cu.getRoles(), cu.isEnabled());
					}
				}
			}
		}
		
		if(user == null) {
			throw new UsernameNotFoundException(
					"No user exists with given credentials : " 
							+ identifier);
		}
		
		
//		System.out.println("Returning user details");
		MyUserDetails ms = new MyUserDetails(user);
		System.out.println(ms);
		System.out.println(ms.getAuthorities());
		return ms;
	}

}
