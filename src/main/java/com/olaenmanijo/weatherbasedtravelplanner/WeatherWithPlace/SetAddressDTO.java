package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Alias("SetAddressDTO")
public class SetAddressDTO {

	String address;
	String date;
	String startTime;
	String endTime;
}
