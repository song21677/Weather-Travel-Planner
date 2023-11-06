package com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.olaenmanijo.weatherbasedtravelplanner.plan.PlaceDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Alias("Item")
public class Item {
	public Item(PlaceDTO placeDTO) {
		this.title = placeDTO.getPlace_name();
		this.contenttypeid = placeDTO.getPlace_category();
		this.addr1 = placeDTO.getRoad_name_adr();
		this.areacode= "area";
		this.sigungucode = "sigungu";
		this.zipcode = "zipcode";
		this.mapx = "123.45";
		this.mapy = "123.45";
		this.createdtime = "2323";
		this.modifiedtime = "2323";
	}

	@JsonProperty("title")
    private String title;
	
	@JsonProperty("addr1")
    private String addr1;
	
	@JsonProperty("areacode")
	private String areacode;
	
	@JsonProperty("sigungucode")
	private String sigungucode;
	
	@JsonProperty("zipcode")
	private String zipcode;
	
	@JsonProperty("mapx")
	private String mapx;
	
	@JsonProperty("mapy")
	private String mapy;
	
	@JsonProperty("contenttypeid")
	private String contenttypeid;
	
	@JsonProperty("createdtime")
	private String createdtime;
	
	@JsonProperty("modifiedtime")
	private String modifiedtime;
}
