package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Alias("First_precinct_tb_DTO")
public class First_precinct_tb_DTO {
	
	 private int FIRST_PRECINCT_NO;
	 private String FIRST_PRECINCT_CD;

	    // getters, setters, etc.


}
