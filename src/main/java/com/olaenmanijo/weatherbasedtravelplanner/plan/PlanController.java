package com.olaenmanijo.weatherbasedtravelplanner.plan;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dao.PlaceDAO;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain.Place;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto.Item;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PlanController {
	@Autowired
	PlaceDAO placeDAO;
	
	// @Autowired
	Plan plan = new Plan();

	@GetMapping("/plan")
	public String plan(@ModelAttribute PlanDTO planDTO) {
		return "planPage/plan";
	}
	
	@ResponseBody
	@PostMapping("/planForm")
	public Plan planForm(@RequestBody PlanDTO planDTO) {
		//Plan plan = new Plan();
		plan.setDaterange("dd");
		plan.setTitle("ddd");
		// placeDAO.selectByNo();
		plan.add(new Item(planDTO.getPlace()));
		log.error("{}", planDTO); 
		log.error("{}, {}, {}", plan.getDaterange(), plan.getTitle(), plan.getPlaces().get(0));
		
		return plan;
	}
	
//	@GetMapping("/test")
//	public void test() throws URISyntaxException {
//		RestTemplate restTemplate = new RestTemplate();
//		URI uri = new URI("http://localhost:8082/planForm");
//		ResponseEntity<Plan> response = restTemplate.postForEntity(uri, new PlanDTO("DD", "DDD", new Place(1, "음식점", "또오리", "충경로135")), Plan.class);
//        Plan plan = response.getBody();
//        log.error("{}", plan);
//	}
	
	@GetMapping("/search")
	public String search(@RequestParam(required = false) String date, @RequestParam(required = false) String area, @RequestParam(required = false) String category, Model model) throws IOException, URISyntaxException {
		model.addAttribute("dateString", date);
		
		if (area != null && category != null) {
			Map<String, String> paramMap = new HashMap<>();
			if (area.equals("6")) area="부산";
			if (category.equals("39")) category="음식점";
	        paramMap.put("category", category);
	        paramMap.put("location", area);
			
	        // ArrayList<Place> places = (ArrayList<Place>) placeDAO.selectByNameAndCategory(paramMap);
	        ArrayList<Place> places = new ArrayList<>();
	        places.add(new Place("음식점", "또오리", "충경로 135", "경기도", "고양시", "123", 123.456, 123.456, LocalDateTime.now(), LocalDateTime.now()));
	        model.addAttribute("places", places);
	        
		}
		return "planPage/searchForm";
	}
}
 

