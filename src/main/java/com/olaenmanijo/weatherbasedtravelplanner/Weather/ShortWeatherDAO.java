package com.olaenmanijo.weatherbasedtravelplanner.Weather;
import java.util.List; 

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class ShortWeatherDAO {

@Autowired
SqlSessionTemplate session;

	
	public int insertAnnounceData(ShortTermAnnounceDTO announceDTO) {
		return session.insert("WeatherMapper.insertAnnounceData",announceDTO);
	}

	public int insertForecastData(ShortTermForecastDTO forecastDTO) {
		return session.insert("WeatherMapper.insertForecastData",forecastDTO);
	}
	
	
	
	
	public List<ShortTermAnnounceDTO> findAll() {
		return session.selectList("WeatherMapper.findAll");
	}
	
	public int countRows() {
        return session.selectOne("WeatherMapper.countRows");
    }

    public List<Integer> fetchIdsOrdered() {
        return session.selectList("WeatherMapper.fetchIdsOrdered");
    }

    public void deleteById(int id) {
    	session.delete("WeatherMapper.deleteById", id);
    }

}
