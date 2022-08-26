package com.dbs.demo.service;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.dbs.demo.dto.AuthenticateRequest;
import com.dbs.demo.dto.AuthenticateResponse;
import com.dbs.demo.response.ResponseHandler;
import com.dbs.demo.util.JwtUtil;

@Service
public class GeneralService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	UserDetailsService userDetailsService;
	
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
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		Cookie loginCookie = new Cookie("jwt", jwt);
		loginCookie.setMaxAge(60*60*10);
		response.addCookie(loginCookie);
		
		return ResponseHandler.generateResponse(200, new AuthenticateResponse(jwt));	
	}

	public ResponseEntity<Object> logoutHandler(HttpServletResponse response) {
		// TODO Auto-generated method stub
		Cookie c = new Cookie("jwt", "");
		c.setMaxAge(0);
		response.addCookie(c);
		return ResponseHandler.generateResponse(200, "Logout Sucessful");
	}
}
