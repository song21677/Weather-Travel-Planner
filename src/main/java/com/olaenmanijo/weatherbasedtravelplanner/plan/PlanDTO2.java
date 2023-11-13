package com.olaenmanijo.weatherbasedtravelplanner.plan;

import com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain.Place;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PlanDTO2 {

	private int detailPlanNo;
	private int planNo;
	String date;
	String realDate;
	String startHour;
	String endHour;
	Place place;
	
	public PlanDTO2(String date, String startHour, String endHour, Place place) {
		this.date = date;
		this.startHour = startHour;
		this.endHour = endHour;
		this.place = place;
	}
}