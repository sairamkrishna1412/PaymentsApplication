package com.dbs.demo.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	public static ResponseEntity<Object> generateResponse(HttpStatus status, Object data) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("status", status.value());
		res.put("data", data);
		
		return new ResponseEntity<Object>(res, status);
	}
	
	public static ResponseEntity<Object> generateResponse(int status, Object data) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("status", status);
		res.put("data", data);
		
		return new ResponseEntity<Object>(res, HttpStatus.valueOf(status));
	}
	
	public static ResponseEntity<Object> generateResponse(HttpStatus status, Object data, String message){
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("status", status.value());
		res.put("data", data);
		res.put("message", message);
		
		return new ResponseEntity<Object>(res, status);
	}
	
	public static ResponseEntity<Object> generateResponse(int status, Object data, String message){
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("status", status);
		res.put("data", data);
		res.put("message", message);
		
		return new ResponseEntity<Object>(res, HttpStatus.valueOf(status));
	}
	
	public static ResponseEntity<Object> generateResponse(HttpStatus status, String message){
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("status", status.value());
		res.put("message", message);
		
		return new ResponseEntity<Object>(res, status);
	}
	
	public static ResponseEntity<Object> generateResponse(int status, String message) {
		// TODO Auto-generated method stub
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("status", status);
		res.put("message", message);
		
		return new ResponseEntity<Object>(res, HttpStatus.valueOf(status));
	}
	
}
