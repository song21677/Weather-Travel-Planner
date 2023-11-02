package com.olaenmanijo.weatherbasedtravelplanner.MainPage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GetNowWeatherServiceImpl implements GetNowWeatherService{
	
	@Autowired
	GetNowWeatherDAO dao;
	
	@Override
	public String getNowWeather(){
		
		List<GetNowWeatherDTO> weatherList = dao.getNowWeather();
	    
	    // DTO 객체를 JSON으로 변환
	    ObjectMapper objectMapper = new ObjectMapper();
	    String weatherListJson;
		try {
			weatherListJson = objectMapper.writeValueAsString(weatherList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			weatherListJson = null;
		}
	    
		return weatherListJson;
	}
	
	@Override
	public String getMediumWeather(){
		
		List<GetMediumWeatherDTO> weatherList = dao.getMediumWeather();
		
		// DTO 객체를 JSON으로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		String weatherListJson;
		try {
			weatherListJson = objectMapper.writeValueAsString(weatherList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			weatherListJson = null;
		}
		
		return weatherListJson;
	}

	@Override
	public List<GetNowWeatherDTO> getNowWeatherMap() {
		return dao.getNowWeather();
	}

}
