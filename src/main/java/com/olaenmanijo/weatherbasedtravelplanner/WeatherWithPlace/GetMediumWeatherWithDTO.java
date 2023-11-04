package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Alias("GetMediumWeatherWithDTO")
public class GetMediumWeatherWithDTO {
	String FORECAST_DAY;
	String WFAM;
	String WFPM;
	int TAMIN;
	int TAMAX;
	int RNSTAM;
	int RNSTPM;
}
