package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherWithPlaceDAO {

	@Autowired
	SqlSession session;
	
	public GetDetailPlanDTO getDetailPlan(int no){
		return session.selectOne("WeatherWithPlace.getDetailPlan",no);
	};
	
	public List<GetShortWeatherWithDTO> withShortWeather(String address) {
		return session.selectList("WeatherWithPlace.withShortWeather",address);
		
	};
	
	public List<GetMediumWeatherWithDTO> withMediumWeather(String address) {
		return session.selectList("WeatherWithPlace.withMediumWeather",address);
	};
}
