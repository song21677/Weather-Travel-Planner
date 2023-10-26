package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public interface ShortWeatherService {

	public void getWeatherData(String nx, String ny, String baseDate, String baseTime , String type , String numOfRows,int second_Precinct_No) throws Exception;

	public void claerDataStore();

	public void submitDataStore();
	
	public void deleteExtraRows();
	
	public List<ShortTermAnnounceDTO> findAll();
}
