package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Alias("GetShortWeatherWithDTO")
public class GetShortWeatherWithDTO {
	String FORECAST_DAY;
	String FORECAST_TIME;
	int TMP;
	String PTY;
	int POP;
	int REH;
	String PCP;
	String SKY;
	int WSD;
	int WAV;
}
