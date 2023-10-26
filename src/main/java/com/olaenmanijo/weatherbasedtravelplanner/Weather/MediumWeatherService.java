package com.olaenmanijo.weatherbasedtravelplanner.Weather;



public interface MediumWeatherService {
	
	public void getWeatherData1(String tmFc, String type , String numOfRows, String regid, int no) throws Exception;

	public void getWeatherData2(String tmFc, String type, String numOfRows, String regid, int no) throws Exception;

	void submit(MediumTerForecastDTO mediumforecastDTO);

}
