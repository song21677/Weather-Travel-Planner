package com.olaenmanijo.weatherbasedtravelplanner.recommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RecommendController {
	
	@Autowired
	RecommendService service;
	
	@GetMapping("/recommend")
	public String recommend(Model model){
		return "recommend/recommendList";
	}
	
	
	@ResponseBody
	@PostMapping("/get-additional-data")
	public ResponseEntity<Object> getAdditionalData(
            @RequestParam Double lat,
            @RequestParam Double lon,
            @RequestParam String address) {
		ResponseEntity<Object> additionalData = service.getAdditionalData(lat, lon, address);
		
		 if (additionalData != null) {
	            return ResponseEntity.ok(additionalData);
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to retrieve additional data.");
	        }
	}
	
	
}
