package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Alias("ShortTermForecastDTO")
public class ShortTermForecastDTO {
	
	
	int SHORT_TERM_FORECAST_NO;
	int IDX;
	int SECOND_PRECINCT_NO;
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
