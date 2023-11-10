package com.olaenmanijo.weatherbasedtravelplanner.recommend;

import org.springframework.http.ResponseEntity;

public interface RecommendService {

	public ResponseEntity<Object> getAdditionalData(Double lat, Double lon, String address);
}
