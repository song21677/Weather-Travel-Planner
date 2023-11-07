package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("SetBlockDTO")
public class SetBlockDTO {

	int plan_No;
	int color;
	int Reason;
	
}
