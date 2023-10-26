package com.olaenmanijo.weatherbasedtravelplanner.domain.plan;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PlanController {
	
	@Autowired
	PlaceDAO placeDAO;

	@GetMapping("/plan")
	public String plan() {
		return "plan";
	}
	
	@GetMapping("/search")
	public String search(@ModelAttribute SearchForm searchForm, @ModelAttribute List<Place> palces) throws IOException, URISyntaxException {
		APIService apiService = new APIService();
		String area = searchForm.getArea();
		String category = searchForm.getCategory();
		if (area != null && category != null) {
			 
			Map<String, String> paramMap = new HashMap<>();
			if (area.equals("6")) area="부산";
			if (category.equals("39")) category="음식점";
	        paramMap.put("category", category);
	        paramMap.put("location", area);
			
	        List<Place> list = placeDAO.selectByNameAndCategory(paramMap);
	        
		}
		return "plan";
	}
	
	@GetMapping("/set")
	public void set() throws IOException, URISyntaxException {
		APIService apiService = new APIService();
		List<Item> items = apiService.requestToTourListBasedAreaAPI();
		if (items != null) {
			for (Item item : items) {
				placeDAO.insertPlace(item);
			}
		}
	}
}
 

