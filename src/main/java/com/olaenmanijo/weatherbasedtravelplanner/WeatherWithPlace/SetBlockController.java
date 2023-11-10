package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/setblock")
public class SetBlockController {
	
	@Autowired
	WeatherWithPlaceService service;
	
	@ResponseBody
	@PostMapping("/{planId}")
	public String giveyourcode(@PathVariable int planId) {
		service.getDetailPlan(planId);
		return null;
	}

}
