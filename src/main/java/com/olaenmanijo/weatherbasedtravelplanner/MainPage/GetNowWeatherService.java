package com.olaenmanijo.weatherbasedtravelplanner.MainPage;

import java.util.List;

public interface GetNowWeatherService {
	
	public String getNowWeather () ;
	public String getMediumWeather () ;
	public List<GetNowWeatherDTO> getNowWeatherMap();

}
