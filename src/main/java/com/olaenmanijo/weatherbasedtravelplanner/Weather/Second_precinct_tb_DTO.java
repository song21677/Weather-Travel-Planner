package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Alias("Second_precinct_tb_DTO")
public class Second_precinct_tb_DTO {
	
	 private int second_Precinct_No;
	 private String second_Precinct;
	 private int first_Precinct_No;
	 private String second_Precinct_Cd;
	 private Double lat;
	 private Double lon;
	 private int nx;
	 private int ny;

	    // getters, setters, etc.


}
