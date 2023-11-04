package com.olaenmanijo.weatherbasedtravelplanner.plan;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain.Place;

@Component
@RequestScope
public class PlanDTO {

	private String date;
	private String time;
	private Place place;
	
	
	public PlanDTO() {	
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	
	@Override
	public String toString() {
		return "PlanDTO [date=" + date + ", time=" + time + ", place=" + place + "]";
	}
}
