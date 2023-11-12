package com.olaenmanijo.weatherbasedtravelplanner.recommend;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Alias("GetAgeDTO")
public class GetAgeDTO {
	
	Double LONGITUDE;
	Double LATITUDE;
	String PLACE_NAME;
	String ROAD_NAME_ADR;
	Double Distance;

}
