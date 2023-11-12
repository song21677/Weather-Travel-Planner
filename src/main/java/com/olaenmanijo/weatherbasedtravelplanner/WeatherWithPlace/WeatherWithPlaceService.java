package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import org.springframework.http.ResponseEntity;

public interface WeatherWithPlaceService {

	public ResponseEntity<Object> getDetailPlan(int no);
	
	public ResponseEntity<Object> setColorBlock(GetDetailPlanDTO list);
}
