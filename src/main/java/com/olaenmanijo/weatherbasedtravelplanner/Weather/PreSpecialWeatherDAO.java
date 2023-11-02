package com.olaenmanijo.weatherbasedtravelplanner.Weather;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PreSpecialWeatherDAO {

	@Autowired
	SqlSession session;
	
	public int setWeatherData(PreSpecialWeatherDTO dto) {
		return session.insert("WeatherMapper.insertPreSpecialWeather",dto);
	}
	
	
	public int keepdatePreSpecialWeather() {
		return session.delete("WeatherMapper.keepdatePreSpecialWeather");
	}
}
