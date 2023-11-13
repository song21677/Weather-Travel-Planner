package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	public ResponseEntity<Object> giveyourcode(@PathVariable int planId) {
		ResponseEntity<Object> response = service.getDetailPlan(planId);
		if (response != null) {
			System.out.println(response);
			// return null; // 삭제
            return ResponseEntity.ok(response);
        } else {
        	return null; // 삭제
            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("현재 연동이 불안정합니다.");
        }
		}

}
