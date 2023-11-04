package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Temporary")
public class TemporaryClass {
	
	@Autowired
	WeatherWithPlaceService service;
	
	@ResponseBody
	@GetMapping("/givecode")
	public String giveyourcode() {
		service.getDetailPlan(1);
		
		return null;
	}

}
