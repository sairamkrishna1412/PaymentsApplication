package com.dbs.demo.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.demo.dto.AuthenticateRequest;
import com.dbs.demo.response.ResponseHandler;
import com.dbs.demo.service.GeneralService;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
public class GeneralController {
	@Autowired
	GeneralService generalService;
	
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
	public ResponseEntity<Object> createAuthToken(@RequestBody AuthenticateRequest req, HttpServletRequest request,HttpServletResponse response) throws Exception{
		final Cookie[] cookies = request.getCookies();
		System.out.println(cookies);
		return generalService.loginHandler(req, response);
	}
	
	@PostMapping("/signOut")
	@ResponseBody
	public ResponseEntity<Object> logoutHandler(HttpServletResponse response) throws Exception{
		return generalService.logoutHandler(response);
	}
}
