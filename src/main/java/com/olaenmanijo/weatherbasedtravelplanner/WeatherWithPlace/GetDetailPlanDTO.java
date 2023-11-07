package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Alias("GetDetailPlanDTO")
public class GetDetailPlanDTO {

	int DETAIL_PLAN_NO;
	String DETAIL_PLAN_YMD;
	String DETAIL_PLAN_HOUR;
	String DETAIL_PLAN_HOUR_END;
	String ROAD_NAME_ADR;
	String PLACE_CATEGORY;
}
