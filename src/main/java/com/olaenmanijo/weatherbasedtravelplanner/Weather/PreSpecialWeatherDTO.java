package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Alias("PreSpecialWeatherDTO")
public class PreSpecialWeatherDTO {
	
	
	int _SQ;
	String TM_FC;
	String PWN;
	String REM;

}
