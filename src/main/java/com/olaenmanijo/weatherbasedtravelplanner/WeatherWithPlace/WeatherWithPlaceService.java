package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import org.springframework.http.ResponseEntity;

public interface WeatherWithPlaceService {

	public ResponseEntity<Object> getDetailPlan(GetplanDTO dto);
	
	public ResponseEntity<Object> setColorBlock(GetDetailPlanDTO list);
}
