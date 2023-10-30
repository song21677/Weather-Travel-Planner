package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MediumWeatherDAO {
	
	@Autowired
	SqlSession session;

	
	public int insertTemp(MediumTerForecastDTO mediumforecastDTO) {
		
		return session.insert("WeatherMapper.insertTemp",mediumforecastDTO);
	}


	public int updateTemp(MediumTerForecastDTO mediumforecastDTO2) {

		return session.update("WeatherMapper.updateTemp",mediumforecastDTO2);
		
	}
	
	public void keepdateTemp() {
		session.delete("WeatherMapper.keepdateTemp");
	}
	
	 
	 public List<String> findMediumAnn() {
		return session.selectList("WeatherMapper.findMediumAnn");
		}
}
