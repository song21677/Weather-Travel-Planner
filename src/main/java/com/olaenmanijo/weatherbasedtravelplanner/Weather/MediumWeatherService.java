package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import java.util.List;

public interface MediumWeatherService {
	
	public void getWeatherData1(String tmFc, String type, String numOfRows, String regid, int no, int no2) throws Exception;

	public void getWeatherData2(String tmFc, String type, String numOfRows, String regid, int no) throws Exception;

	void submit(MediumTerForecastDTO mediumforecastDTO);
	
	public void keepdateTemp();
	
	public List<String> findMediumAnn();


}
