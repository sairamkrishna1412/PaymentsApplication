package com.dbs.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.demo.response.ResponseHandler;
import com.dbs.demo.service.GeneralService;

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
}
