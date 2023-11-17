package com.olaenmanijo.weatherbasedtravelplanner.MainPage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProudConetentServiceImpl implements ProudConetentService {

	@Autowired
	ProudConetentDAO dao;
	
	
	public List<GetRecommendDTO> community(){
		
		return dao.community();
	}

}
