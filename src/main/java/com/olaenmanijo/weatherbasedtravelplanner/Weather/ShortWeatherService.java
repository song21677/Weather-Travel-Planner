package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import java.util.List;

public interface ShortWeatherService {

	public void getWeatherData(String nx, String ny, String baseDate, String baseTime , String type , String numOfRows,int second_Precinct_No) throws Exception;
	
	public List<ShortTermAnnounceDTO> findAll();

	public void claerDataStore();

	public void submitDataStore();
	
	public void deleteExtraRows();
	
}
