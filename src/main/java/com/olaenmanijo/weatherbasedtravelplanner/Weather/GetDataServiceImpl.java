package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetDataServiceImpl implements GetDataService {
	
	@Autowired
	GetDataDAO dao;

	@Override
	public List<Second_precinct_tb_DTO> getSecondPrecinctData() {
		
		return dao.getSecondPrecinctData();
	}

	@Override
	public List<First_precinct_tb_DTO> getFirstPrecinctData() {
		
		return dao.getFirstPrecinctData();
	}
	
	
}
