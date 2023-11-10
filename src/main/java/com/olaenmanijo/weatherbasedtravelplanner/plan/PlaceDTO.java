package com.olaenmanijo.weatherbasedtravelplanner.plan;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaceDTO {
	
	private int place_no;
	private String place_category;
	private String place_name;
	private String road_name_adr;
	
	public PlaceDTO() {
		
		
	}
}
