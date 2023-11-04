package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherWithPlaceServiceImpl implements WeatherWithPlaceService {

	@Autowired
	WeatherWithPlaceDAO dao;
	
	private LocalDateTime dateTime = LocalDateTime.now();
	
	public void getDetailPlan(int no){
		
		setColorBlock(dao.getDetailPlan(no));
		
	}
	
	public void setColorBlock(GetDetailPlanDTO dto) {
		String place_Category = dto.getPLACE_CATEGORY();
		
		int plan_No = dto.getDETAIL_PLAN_NO();
		String plan_Date = dto.getDETAIL_PLAN_DATE();
		String plan_Hour = dto.getDETAIL_PLAN_HOUR();
		String plan_Ymd = dto.getDETAIL_PLAN_YMD();
		String road_Name_Adr = dto.getROAD_NAME_ADR();
		String address = giveMeYourPlace(road_Name_Adr);
		String currentTime = dateTime.format(DateTimeFormatter.ofPattern("HH"));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate planLocalDateTime = LocalDate.parse(plan_Ymd, formatter);
		// 날짜 차이 계산
		long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(planLocalDateTime, dateTime.toLocalDate());
	    
	    if (daysBetween >= 3) {
	    	withMediumWeather(address);
        } else {
        	withShortWeather(address);
        }
	}
	
	
	// 행정 구역 추출기 ("xx시 xx구로 변환")
	public String giveMeYourPlace(String address) {
			String province = "";
		    String city = "";
		    String district = "";
		    String yourAddress = null;

		    String[] parts = address.split(" "); // 공백을 기준으로 주소를 분리

		    if (address.contains("도") && address.contains("시") && address.contains("구")) {
		        // "도" 와 "시"와 "구"가 모두 포함된 경우
		    	for (int i = 0; i < parts.length; i++) {
		    		if (parts[i].endsWith("도")) {
		    			province = parts[i];
		    		}
			        if (parts[i].endsWith("시")) {
			            city = parts[i];
			        }
			        if (parts[i].endsWith("구")) {
			            district = parts[i];
			        }
			        
			    }
		    	
		    	yourAddress = province + " " + city + " " + district;
		   
		    } else if (address.contains("도") && address.contains("시")) {
		        // "도"와 "시"가 모두 포함된 경우
		    	for (int i = 0; i < parts.length; i++) {
		    		if (parts[i].endsWith("도")) {
		    			province = parts[i];
		    		}
			        if (parts[i].endsWith("시")) {
			            city = parts[i];
			        }
		        }
		    	
		    	yourAddress = province + " " + city;
		    	
		    } else if (address.contains("시") && address.contains("구")) {
		        // "시"와 "구"가 모두 포함된 경우
		    	for (int i = 0; i < parts.length; i++) {
		    		if (parts[i].endsWith("시")) {
		    			province = parts[i];
		    		}
			        if (parts[i].endsWith("구")) {
			            district = parts[i];
			        }
			        
			        
			        yourAddress = province + " " + district;
		        }
		    } else if (address.contains("도") && address.contains("군")) {
		        // "도"와 "군"이 모두 포함된 경우
		    	for (int i = 0; i < parts.length; i++) {
		    		if (parts[i].endsWith("도")) {
		    			province = parts[i];
		    		}
			        if (parts[i].endsWith("군")) {
			            district = parts[i];
			        }
			        
			        		        
		        }
		    	
		    	  yourAddress = province + " " + district;
		    	
		    	
		    } else {
		        // 다른 형태의 주소는 분류할 수 없음
		    	System.out.println("분류할 수 없는 주소 형식입니다.");
		    }
		    
		    System.out.println("해당 행정구역은 " + yourAddress + "입니다");
		    
		    return yourAddress;
	}
	
	
	public void withShortWeather(String address) {
		dao.withShortWeather(address);
		System.out.println(dao.withShortWeather(address));
	};

	public void withMediumWeather(String address) {
		dao.withMediumWeather(address);
		
	};
	
	
}
