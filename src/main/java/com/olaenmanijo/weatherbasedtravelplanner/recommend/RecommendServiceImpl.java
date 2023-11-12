package com.olaenmanijo.weatherbasedtravelplanner.recommend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RecommendServiceImpl implements RecommendService {
	
	
	@Autowired
	RecommendDAO dao;
	
	private Map<String, Object> maps;

	@Override
	public ResponseEntity<Object> getAdditionalData(Double lat, Double lon, String address, String locate) {
           
		
		if(lat==0||lon==0||address=="0"){
			lon=126.9800083;
			lat=37.5635694;
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
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("latitude", lat);
		parameters.put("longitude", lon);
		parameters.put("address", address);
		maps = parameters;
			
		
		
		
		
		  Map<String, Object> responseData = new HashMap<>();
	        responseData.put("latitude", lat);
	        responseData.put("longitude", lon);
	        responseData.put("address", address);
	        

	        // ResponseEntity로 응답 생성
	        return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<Object> getAdditionalData2(String locate) {
		
		switch (locate) {
	    case "shop":
	        locate = "쇼핑";
	        break;
	    case "restaurant":
	        locate = "음식점";
	        break;
	    case "area":
	        locate = "관광지";
	        break;
	    case "accommodation":
	        locate = "숙박";
	        break;
	    case "festival":
	        locate = "행사/공연/축제";
	        break;
	    default:
	        locate = "알수없음";
	        break;
	}

		maps.put("locate", locate);
		
		List<GetLocationDTO> list = dao.getLocation(maps);
		for (GetLocationDTO dto : list) {
			double lat1 = (double) maps.get("latitude");
			double lon1 = (double) maps.get("longitude");
			double lat2 = dto.getLATITUDE();
			double lon2 = dto.getLONGITUDE();
			double distance = haversine(lat1, lon1, lat2, lon2);
			
			dto.setDistance(Math.round(distance*100)/100.0);
		};
		  Map<String, Object> responseData = new HashMap<>();
		   responseData.put("list",list);
	       responseData.put("locate", locate);
	        

	        // ResponseEntity로 응답 생성
	        return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
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
