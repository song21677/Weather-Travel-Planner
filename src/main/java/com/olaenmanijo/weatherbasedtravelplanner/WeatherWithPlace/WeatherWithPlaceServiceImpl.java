package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherWithPlaceServiceImpl implements WeatherWithPlaceService {

	@Autowired
	WeatherWithPlaceDAO dao;
	
	public void getDetailPlan(int no){
		
		setColorBlock(dao.getDetailPlan(no));
		
	}
	
	public void setColorBlock(GetDetailPlanDTO dto) {
		
		dto.getDETAIL_PLAN_NO();
		dto.getDETAIL_PLAN_DATE();
		dto.getDETAIL_PLAN_HOUR();
		dto.getDETAIL_PLAN_YMD();
		dto.getPLACE_CATEGORY();
		dto.getROAD_NAME_ADR();
	}
}
