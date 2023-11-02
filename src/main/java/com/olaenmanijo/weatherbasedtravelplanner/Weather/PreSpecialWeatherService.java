package com.olaenmanijo.weatherbasedtravelplanner.Weather;

public interface PreSpecialWeatherService {

	void setWeatherData(String fromTmFc, String toTmFc, String type, String numOfRows) throws Exception;
	
	int keepdateSpecialWeather();

}
