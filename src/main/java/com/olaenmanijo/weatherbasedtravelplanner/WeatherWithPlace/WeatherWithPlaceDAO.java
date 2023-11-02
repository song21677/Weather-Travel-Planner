package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherWithPlaceDAO {

	@Autowired
	SqlSession session;
	
	public GetDetailPlanDTO getDetailPlan(int no){
		return session.selectOne("WeatherWithPlace.getDetailPlan",no);
	}
}
