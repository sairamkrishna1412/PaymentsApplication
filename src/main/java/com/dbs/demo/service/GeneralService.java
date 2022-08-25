package com.dbs.demo.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class GeneralService {
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
}
