package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;



import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherWithPlaceDAO {

	@Autowired
	SqlSession session;
	
	public GetDetailPlanDTO getDetailPlan(GetplanDTO dto){
		return session.selectOne("WeatherWithPlace.getDetailPlan",dto);
	};
	
	public List<GetShortWeatherWithDTO> withShortWeather(SetAddressDTO dto) {
		return session.selectList("WeatherWithPlace.withShortWeather",dto);
		
	};
	
	public GetMediumWeatherWithDTO withMediumWeather(SetAddressDTO dto) {
		return session.selectOne("WeatherWithPlace.withMediumWeather",dto);
	};
	
	public void SetBlock(SetBlockDTO dto) {
		
		// session.insert("WeatherWithPlace.setBlock",dto);
	}
	
	public void setBlockUpdate(SetBlockDTO dto) {
		// session.update("WeatherWithPlace.setBlockUpdate",dto);
	}
	
	public SetBlockDTO checkBlock (int plan_No) {
		return session.selectOne("WeatherWithPlace.checkBlock",plan_No);
	}
	
}
