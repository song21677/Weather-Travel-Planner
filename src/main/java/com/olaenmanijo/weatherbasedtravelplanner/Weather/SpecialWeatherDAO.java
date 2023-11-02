package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class SpecialWeatherDAO {
	
	@Autowired
	SqlSession session;
	
	public int setWeatherData(SpecialWeatherDTO dto) {
		return session.insert("WeatherMapper.insertSpecialWeather",dto);
	}
	
	
	public int keepdateSpecialWeather() {
		return session.delete("WeatherMapper.keepdateSpecialWeather");
	}

}
