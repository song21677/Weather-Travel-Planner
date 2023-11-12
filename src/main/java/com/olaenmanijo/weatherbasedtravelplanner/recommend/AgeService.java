package com.olaenmanijo.weatherbasedtravelplanner.recommend;

import org.springframework.http.ResponseEntity;

public interface AgeService {
	
	public ResponseEntity<Object> getAgeData(Double lat, Double lon);

	public ResponseEntity<Object> getAgeData2(String age);

}
