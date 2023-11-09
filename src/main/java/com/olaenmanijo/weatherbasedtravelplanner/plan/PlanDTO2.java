package com.olaenmanijo.weatherbasedtravelplanner.plan;

import com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain.Place;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PlanDTO2 {

	private int planNo;
	String date;
	String time;
	Place place;
	
	public PlanDTO2(String date2, String time2, Place place2) {
		date = date2;
		time = time2;
		place = place2;
	}
}
