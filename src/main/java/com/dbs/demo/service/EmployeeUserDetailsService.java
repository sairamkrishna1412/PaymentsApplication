//package com.dbs.demo.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.dbs.demo.model.Employee;
//import com.dbs.demo.repo.EmployeeRepo;
//
//public class EmployeeUserDetailsService implements UserDetailsService{
//	
//	@Autowired
//	EmployeeRepo empRepo;
//	
//	@Override
//	public UserDetails loadUserByUsername(String employeeUsername) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		Employee emp = empRepo.findByEmployeeUsername(employeeUsername);
//		if (emp == null) {
//            throw new UsernameNotFoundException(employeeUsername);
//        }
//		UserDetails empDetails = new org.springframework.security.core.userdetails.
//                User(emp.getEmployeeUsername(), emp.getEmployeePassword(), null);
//		return empDetails;
//	}
//
//}
