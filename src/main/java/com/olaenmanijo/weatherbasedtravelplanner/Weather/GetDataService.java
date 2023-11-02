package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import java.util.List;

import org.springframework.stereotype.Service;


public interface GetDataService {

	List<Second_precinct_tb_DTO> getSecondPrecinctData();
	
	List<First_precinct_tb_DTO> getFirstPrecinctData();

}
