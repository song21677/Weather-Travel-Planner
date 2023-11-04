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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dao.PlaceDAO;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain.Place;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PlanController {
	@Autowired
	PlaceDAO placeDAO;
	
	@Autowired
	Plan plan;

	@GetMapping("/plan")
	public String plan(@ModelAttribute PlanDTO planDTO) {
		return "planPage/plan";
	}
	
	@ResponseBody
	@PostMapping("/planForm")
	public void planForm(@RequestBody PlanDTO planDTO) {
		plan.setDaterange("dd");
		plan.setTitle("ddd");
		plan.add(planDTO);
		planDTO.getPlace().setRoad_name_adr("ss");
		log.error("{}, {}, {}", plan.getDaterange(), plan.getTitle(), plan.getPlanDTOs().get(0));
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(required = false) String date, @RequestParam(required = false) String area, @RequestParam(required = false) String category, Model model) throws IOException, URISyntaxException {
		model.addAttribute("dateString", date);
		
		if (area != null && category != null) {
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
 

