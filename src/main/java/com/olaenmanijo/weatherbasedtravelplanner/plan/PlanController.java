package com.olaenmanijo.weatherbasedtravelplanner.plan;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dao.PlaceDAO;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain.Place;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PlanController {
	@Autowired
	PlaceDAO placeDAO;

	@GetMapping("/plan")
	public String plan(@ModelAttribute PlanDTO planDTO) {
		return "planPage/plan";
	}
	
	@GetMapping("/planForm")
	public String planForm(@ModelAttribute PlanDTO planDTO) {
		
		
		return "planPage/planForm";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(required = false) String date, @RequestParam(required = false) String area, @RequestParam(required = false) String category, Model model) throws IOException, URISyntaxException {
		if (area != null && category != null) {
			model.addAttribute("dateString", date);
			 
			Map<String, String> paramMap = new HashMap<>();
			if (area.equals("6")) area="부산";
			if (category.equals("39")) category="음식점";
	        paramMap.put("category", category);
	        paramMap.put("location", area);
			
	        ArrayList<Place> places = (ArrayList<Place>) placeDAO.selectByNameAndCategory(paramMap);
	        model.addAttribute("places", places);
	        
		}
		return "planPage/searchForm";
	}
}
 

