package com.olaenmanijo.weatherbasedtravelplanner.MainPage;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Alias("GetMediumWeatherDTO")
@Getter
@Setter
public class GetMediumWeatherDTO {
	int SECOND_PRECINCT_NO;
	String FORECAST_DAY;
	String WFAM;
	String WFPM;
	int TAMIN;
	int TAMAX;
	int RNSTAM;
	int RNSTPM;
	}


