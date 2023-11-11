package com.olaenmanijo.weatherbasedtravelplanner.recommend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RecommendServiceImpl implements RecommendService {

	@Override
	public ResponseEntity<Object> getAdditionalData(Double lat, Double lon, String address) {
		
		
		if(lat==0||lon==0||address=="0"){
			lat=126.9800083;
			lon=37.5635694;
			address="서울특별시";
		}
		if(address.contains(" ")) {
		address = address.substring(0, address.indexOf(" "));
		}
		
		if (address.contains("경남") || address.contains("경상남도")) {
            address = "경상남도";
        }else if (address.contains("경북") || address.contains("경상북도")) {
            address = "경상북도";
        }else if (address.contains("제주")){
            address = "제주도";
        }else if (address.contains("강원")){
            address = "강원특별자치도";
        }else if (address.contains("경기")){
            address = "경기도";
        }else if (address.contains("충북") || address.contains("충청북도")) {
            address = "충청북도";
        }else if (address.contains("충남") || address.contains("충청남도")) {
            address = "충청남도";
        }else if (address.contains("전북") || address.contains("전라북도")) {
            address = "전라북도";
        }else if (address.contains("전남") || address.contains("전라남도")) {
            address = "전라남도";
        }else if (address.contains("서울")){
            address = "서울";
        }else if (address.contains("인천")){
            address = "인천";
        }else if (address.contains("대전")){
            address = "대전";
        }else if (address.contains("대구")){
            address = "대구";
        }else if (address.contains("부산")){
            address = "부산";
        }else if (address.contains("세종")){
            address = "세종";
        }else if (address.contains("울산")){
            address = "울산";
        }else {address = "서울";}
		
		

		
        

		
		System.out.println(lat+"   "+lon+" "+ address);
		
		
		
		  Map<String, Object> responseData = new HashMap<>();
	        responseData.put("latitude", lat);
	        responseData.put("longitude", lon);
	        responseData.put("address", address);

	        // ResponseEntity로 응답 생성
	        return new ResponseEntity<>(responseData, HttpStatus.OK);
	}

}
