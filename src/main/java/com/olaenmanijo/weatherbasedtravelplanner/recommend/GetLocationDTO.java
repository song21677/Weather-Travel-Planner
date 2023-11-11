package com.olaenmanijo.weatherbasedtravelplanner.recommend;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Alias("GetLocationDTO")
public class GetLocationDTO {
	int PLACE_NO;
	String PLACE_NAME;
	String ROAD_NAME_ADR;
	Double LONGITUDE;
	Double LATITUDE;
	Double distance;
}
