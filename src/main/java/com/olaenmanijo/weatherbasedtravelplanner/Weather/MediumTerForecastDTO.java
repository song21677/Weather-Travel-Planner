package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("MediumTerForecastDTO")
@Getter
@Setter
@ToString
public class MediumTerForecastDTO {
	
	int MEDIUM_TERM_FCST_NO;
	int FIRST_PRECINCT_NO;
	int SECOND_PRECINCT_NO;
	String ANNOUNCE_DAY;
	String FORECAST_DAY;
	int TAMIN;
	int TAMAX;
	int RNSTAM;
	int RNSTFM;
	String WFAM;
	String WFPM;
}
