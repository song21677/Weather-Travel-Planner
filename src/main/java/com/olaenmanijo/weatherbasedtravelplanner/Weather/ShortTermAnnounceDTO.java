package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Alias("ShortTermAnnounceDTO")
public class ShortTermAnnounceDTO {
	
	int IDX;
	String ANNOUNCE_DAY;
	String ANNOUNCE_TIME;

}
