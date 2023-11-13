package com.olaenmanijo.weatherbasedtravelplanner.plan;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain.Place;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto.Item;

import lombok.Getter;
import lombok.Setter;

@Component
@RequestScope
@Setter
@Getter
public class PlanDTO {

   private String date;
   private String startHour;
   private String endHour;
   private String place_no;
   private String place_color;
   
   
   public PlanDTO() {   
   }
   
   public PlanDTO(String date, String startTime, String endTime, String place_no, String place_color) {
		super();
		this.date = date;
		this.startHour = startTime;
		this.endHour = endTime;
		this.place_no = place_no;
		this.place_color = place_color;
	}
  
   @Override
   public String toString() {
      return "PlanDTO [date=" + date + ", time=" + startHour + ", endTime=" + endHour + ", place_no=" + place_no + "]";
   }

}