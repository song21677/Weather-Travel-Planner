package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Alias("SpecialWeatherDTO")
public class SpecialWeatherDTO {

	String TM_FC;
	String AREA_NAME;
	String WARN_VAR;
	int WARN_STRESS;
	int COMMAND;
	String TIME;
	int CANCEL;
	
}
