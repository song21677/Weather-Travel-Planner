package com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.olaenmanijo.weatherbasedtravelplanner.plan.PlaceDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Setter
@Getter
public class Place {

	private int place_no;
	private String place_category;
	private String place_name;
	private String road_name_adr;
	private String area;
	private String sigungu;
	private String zip_code;
	private double longitude;
	private double latitude;
	//private LocalDateTime create_time;
	private String create_time;
	//private LocalDateTime modified_time;
	private String modified_time;
	
	public Place(String place_category, String place_name, String road_name_adr, String area,
			String sigungu, String zipcode, double longitude, double latitude, String createdtime,
			String modifiedtime) {
		this.place_category = place_category;
		this.place_name = place_name;
		this.road_name_adr = road_name_adr;
		this.area = area;
		this.sigungu = sigungu;
		this.zip_code = zipcode;
		this.longitude = longitude;
		this.latitude = latitude;
		this.create_time = createdtime;
		this.modified_time = modifiedtime;
	}

	public Place(int place_no, String place_category, String place_name, String road_name_adr) {
		super();
		this.place_no = place_no;
		this.place_category = place_category;
		this.place_name = place_name;
		this.road_name_adr = road_name_adr;
	}	

}
