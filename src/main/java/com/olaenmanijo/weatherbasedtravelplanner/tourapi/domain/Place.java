package com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain;

public class Place {

	String place_category;
	String place_name;
	String road_name_adr;
	int longitude;
	int latitude;
	
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
		return "Place [place_category=" + place_category + ", place_name=" + place_name + ", road_name_adr="
				+ road_name_adr + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
}
