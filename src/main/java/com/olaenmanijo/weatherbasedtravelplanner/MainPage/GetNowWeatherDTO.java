package com.olaenmanijo.weatherbasedtravelplanner.MainPage;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Alias("GetNowWeatherDTO")
public class GetNowWeatherDTO {
	
	int SECOND_PRECINCT_NO;
	String FORECAST_DAY;
	String FORECAST_TIME;
	int TMP;
	int REH;
	String PTY;
	String PCP;
	String SKY;

}
