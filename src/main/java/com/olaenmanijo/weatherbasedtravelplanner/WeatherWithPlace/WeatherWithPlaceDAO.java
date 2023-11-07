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
	
	public List<GetShortWeatherWithDTO> withShortWeather(SetAddressDTO dto) {
		return session.selectList("WeatherWithPlace.withShortWeather",dto);
		
	};
	
	public List<GetMediumWeatherWithDTO> withMediumWeather(SetAddressDTO dto) {
		return session.selectList("WeatherWithPlace.withMediumWeather",dto);
	};
	
	public void SetBlock(SetBlockDTO dto) {
		session.insert("WeatherWithPlace.setBlock",dto);
	}
}
