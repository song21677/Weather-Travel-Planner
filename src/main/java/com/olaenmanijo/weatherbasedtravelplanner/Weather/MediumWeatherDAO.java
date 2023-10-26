package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MediumWeatherDAO {
	
	@Autowired
	SqlSession session;

	
	public int insertTemp(MediumTerForecastDTO mediumforecastDTO) {
		
		return 0;
	}
}
