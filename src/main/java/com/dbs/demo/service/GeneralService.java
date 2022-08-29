package com.dbs.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.dbs.demo.dto.AuthenticateRequest;
import com.dbs.demo.dto.AuthenticateResponse;
import com.dbs.demo.model.Customer;
import com.dbs.demo.model.Employee;
import com.dbs.demo.model.MyUserDetails;
import com.dbs.demo.model.Transaction;
import com.dbs.demo.repo.CustomerRepo;
import com.dbs.demo.repo.EmployeeRepo;
import com.dbs.demo.repo.TransactionRepo;
import com.dbs.demo.response.ResponseHandler;
import com.dbs.demo.util.JwtUtil;

@Service
public class GeneralService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	EmployeeRepo er;
	
	@Autowired
	CustomerRepo cr;
	
	@Autowired
	TransactionRepo tr;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	public Map<String, Object> isLive() {
		String day = LocalDate.now().getDayOfWeek().name();
		Map<String, Object> data = new HashMap<>();
		
		if(day.equals("SATURDAY") || day.equals("SUNDAY")) {
			data.put("isLive" , false);
		}else {
			data.put("isLive", true);
		}
		return data;
	}

	public ResponseEntity<Object> loginHandler(AuthenticateRequest req, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
			);
		}catch(Exception e) {
			System.out.println("Authentication Failed");
			ResponseHandler.generateResponse(400, "Incorrect username or password");
		}
		
		final MyUserDetails userDetails = (MyUserDetails) myUserDetailsService.loadUserByUsername(req.getUsername());
		
		HashMap<String, Object> resData = new HashMap<>();
		Employee emp = null;
		Customer cust = null;
		List<Transaction> transactions = null;
		
		if(userDetails.getRole().equals("employee")) {
			emp = er.findById(userDetails.getId()).orElse(null);
			emp.setEmployeePassword(null);
			resData.put("user", emp);
			List<Transaction> Pendingtransactions = tr.findEmployeePendingTransactionsById();
			resData.put("pendingTransactions", Pendingtransactions);
			transactions = tr.getFinalizedTransactions(emp.getEmployeeId());
			resData.put("finalizedTransactions", transactions);
		}else {
			cust = cr.findById(userDetails.getId()).orElse(null);
			resData.put("user", cust);
			transactions = tr.getCustomerTransactions(cust.getCustomerId());
			resData.put("transactions", transactions);
		}
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		Cookie loginCookie = new Cookie("jwt", jwt);
		loginCookie.setMaxAge(60*60*10);
		loginCookie.setDomain("localhost");
		response.addCookie(loginCookie);
		resData.put("jwt", jwt);
		
		return ResponseHandler.generateResponse(200, resData);	
	}

	public ResponseEntity<Object> logoutHandler(HttpServletResponse response) {
		// TODO Auto-generated method stub
		Cookie c = new Cookie("jwt", "");
		c.setMaxAge(0);
		response.addCookie(c);
		return ResponseHandler.generateResponse(200, "Logout Sucessful");
	}
}
