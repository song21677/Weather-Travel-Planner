package com.olaenmanijo.weatherbasedtravelplanner.domain.plan;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PlanController {

	@GetMapping("/plan")
	public String plan() {
		return "plan";
	}
	
	@GetMapping("/search")
	public String search(@ModelAttribute SearchForm searchForm) throws IOException, URISyntaxException {
		APIService apiService = new APIService();
		if (searchForm.getArea() != null && searchForm.getCategory() != null) {
			apiService.requestToTourListBasedAreaAPI(searchForm);
		}
		return "plan";
	}
}
 

