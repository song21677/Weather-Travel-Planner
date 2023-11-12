package com.olaenmanijo.weatherbasedtravelplanner.recommend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AgeServiceImpl implements AgeService {

	@Autowired
	AgeDAO dao;
	
	private Map<String, Object> maps;
	
	
	public ResponseEntity<Object> getAgeData(Double lat, Double lon) {
		
		if(lat==0||lon==0){
			lon=126.9800083;
			lat=37.5635694;
		}
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("latitude", lat);
		parameters.put("longitude", lon);
		maps = parameters;
		
		
		  Map<String, Object> responseData = new HashMap<>();
	        responseData.put("latitude", lat);
	        responseData.put("longitude", lon);
		 return new ResponseEntity<>(responseData, HttpStatus.OK);}
	
	
	public ResponseEntity<Object> getAgeData2(String ageString) {
		int age = 0;
		if (ageString.startsWith("age")) {
		age = Integer.parseInt(ageString.substring(3));
		}
		
		if(age==0) {
			System.out.println("잘못된 요청입니다.");
			return null;
		}
		
		Map<String ,Integer> getlocate = new HashMap<String, Integer>();
		if(age==10) {
			getlocate.put("start", 10);
			getlocate.put("end",19);
		}
		
		if(age==20) {
			getlocate.put("start", 20);
			getlocate.put("end",29);
		}
		else if(age==30) {
			getlocate.put("start", 30);
			getlocate.put("end",39);
		}
		else if(age==40) {
			getlocate.put("start", 40);
			getlocate.put("end",49);
		}
		else if(age==50) {
			getlocate.put("start", 50);
			getlocate.put("end",99);
		}
		
		double lat1 = (double) maps.get("latitude");
		double lon1 = (double) maps.get("longitude");
		
		List<GetAgeDTO> list =	dao.getAge(getlocate);
		for (GetAgeDTO dto : list) {
			double distance = Math.round(haversine(lat1,lon1,dto.getLATITUDE(),dto.getLONGITUDE())*100)/100.0;
			
			
			dto.setDistance(distance);
			
		}
		
		Map<String, Object> responseData = new HashMap<>();
        responseData.put("age", ageString);
        responseData.put("list", list);
		
		return new ResponseEntity<>(responseData, HttpStatus.OK);}


//좌표계산기
	private static double haversine(double lat1, double lon1, double lat2, double lon2) {
      final int R = 6371; // 지구 반지름 (km)

      double dLat = Math.toRadians(lat2 - lat1);
      double dLon = Math.toRadians(lon2 - lon1);

      double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                 Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                 Math.sin(dLon / 2) * Math.sin(dLon / 2);

      double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

      return R * c; // 거리 (km)
  }
	}

