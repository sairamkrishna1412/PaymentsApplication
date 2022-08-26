package com.dbs.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.demo.dto.AuthenticateRequest;
import com.dbs.demo.dto.AuthenticateResponse;
import com.dbs.demo.response.ResponseHandler;
import com.dbs.demo.service.GeneralService;
import com.dbs.demo.service.MyUserDetailsService;
import com.dbs.demo.util.JwtUtil;

@RestController
public class GeneralController {
	@Autowired
	GeneralService generalService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/status")
	@ResponseBody
	public ResponseEntity<Object> getStatus() {
		try {			
			Object res = generalService.isLive();
			return ResponseHandler.generateResponse(HttpStatus.OK, res);
		}catch(Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,
					"Something went wrong, Please try again.");
		}
	}
	
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<Object> createAuthToken(@RequestBody AuthenticateRequest req) throws Exception{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
			);
		}catch(Exception e) {
			System.out.println("Authentication Failed");
			throw new Exception("Incorrect Credentials", e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseHandler.generateResponse(200, new AuthenticateResponse(jwt));	
	}
}
