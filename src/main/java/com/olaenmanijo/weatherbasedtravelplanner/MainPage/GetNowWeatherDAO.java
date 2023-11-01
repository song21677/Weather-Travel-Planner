package com.olaenmanijo.weatherbasedtravelplanner.MainPage;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GetNowWeatherDAO {

	@Autowired
	SqlSession session;
	
	public List<GetNowWeatherDTO> getNowWeather(){
		return session.selectList("MainPageMapper.getNowWeather");
		
	}
	
	public List<GetMediumWeatherDTO> getMediumWeather(){
		return session.selectList("MainPageMapper.getMediumWeather");
		
	}
	
	
}
