package com.olaenmanijo.weatherbasedtravelplanner.tourapi.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dao.PlaceDAO;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain.Place;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto.Item;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto.Items;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.service.APIService;

@Controller
public class APIController {
	
	@Autowired
	PlaceDAO placeDAO;
	
	Map<String, String> area = new HashMap<String, String>() {{
		put("1", "서울");
		put("2", "인천");
		put("3", "대전");
		put("4", "대구");
		put("5", "광주");
		put("6", "부산");
		put("7", "울산");
		put("8", "세종특별자치시");
		put("31", "경기도");
		put("32", "강원특별자치도");
		put("33", "충청북도");
		put("34", "충청남도");
		put("35", "경상북도");
		put("36", "경상남도");
		put("37", "전라북도");
		put("38", "전라남도");
		put("39", "제주도");
	}};
	
	Map<String, String> category = new HashMap<String, String>() {{
		put("12", "관광지");
		put("14", "문화시설");
		put("15", "행사/공연/축제");
		put("25", "여행코스");
		put("28", "레포츠");
		put("32", "숙박");
		put("38", "쇼핑");
		put("39", "음식점");
	}};

	@GetMapping("/set")
	public void set() throws IOException, URISyntaxException {
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	
		
		APIService apiService = new APIService();
		List<Item> items = apiService.requestToTourListBasedAreaAPI();
		if (items != null) {
			for (Item item : items) {
				LocalDateTime createdTime = LocalDateTime.parse(item.getCreatedtime(), format);
				LocalDateTime modifiedTime = LocalDateTime.parse(item.getModifiedtime(), format);
				
				Place place = new Place(category.get(item.getContenttypeid()), item.getTitle(), 
						item.getAddr1(), area.get(item.getAreacode()), item.getSigungucode(),
						item.getZipcode(), Double.parseDouble(item.getMapx()), Double.parseDouble(item.getMapy()),
						createdTime, modifiedTime);
				placeDAO.insertPlace(place);
			}
		}
	}
}
