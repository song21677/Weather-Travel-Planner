package com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Place {

	private int place_no;
	private String place_category;
	private String place_name;
	private String road_name_adr;
	private int longitude;
	private int latitude;
	
	public int getPlace_no() {
		return place_no;
	}
	public void setPlace_no(int place_no) {
		this.place_no = place_no;
	}
	public String getPlace_category() {
		return place_category;
	}
	public void setPlace_category(String place_category) {
		this.place_category = place_category;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getRoad_name_adr() {
		return road_name_adr;
	}
	public void setRoad_name_adr(String road_name_adr) {
		this.road_name_adr = road_name_adr;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "Place [place_no=" + place_no + ", place_category=" + place_category + ", place_name=" + place_name + ", road_name_adr="
				+ road_name_adr + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
}
