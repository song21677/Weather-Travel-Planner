package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class GetDataDAO {

	@Autowired
	SqlSessionTemplate session;

	
		 
	public List<Second_precinct_tb_DTO> getSecondPrecinctData() {
	     return session.selectList("WeatherMapper.getSecondPrecinctData");
	}
	
	public List<First_precinct_tb_DTO> getFirstPrecinctData() {
	     return session.selectList("WeatherMapper.getFirstPrecinctData");
	}

}
