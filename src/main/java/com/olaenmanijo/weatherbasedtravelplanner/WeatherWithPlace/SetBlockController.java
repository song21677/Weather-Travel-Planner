package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SetBlockController {
	
	@Autowired
	WeatherWithPlaceService service;
	
	@ResponseBody
	@PostMapping("/setblock")
	public ResponseEntity<Object> giveyourcode(@RequestParam String date, @RequestParam String startHour, @RequestParam String endHour, @RequestParam String place_no) {
		GetplanDTO dto = new GetplanDTO();
		dto.setDate(date);
		dto.setStartHour(startHour);
		dto.setEndHour(endHour);
		dto.setPlaceNo(Integer.parseInt(place_no));
		ResponseEntity<Object> response = service.getDetailPlan(dto);
		if (response != null) {
			System.out.println(response);
			// return null; // 삭제
            return ResponseEntity.ok(response);
        } else {
        	//return null; // 삭제
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("현재 연동이 불안정합니다.");
        }
		}

}
