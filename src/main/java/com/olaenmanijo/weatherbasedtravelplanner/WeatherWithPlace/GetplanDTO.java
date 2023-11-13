package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Alias("GetplanDTO")
public class GetplanDTO {

	String date;
	String startHour;
	String endHour;
	int placeNo;
}
