package com.olaenmanijo.weatherbasedtravelplanner.tourapi.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dao.PlaceDAO;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto.Item;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto.Items;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.service.APIService;

@Controller
public class APIController {
	
	@Autowired
	PlaceDAO placeDAO;

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
