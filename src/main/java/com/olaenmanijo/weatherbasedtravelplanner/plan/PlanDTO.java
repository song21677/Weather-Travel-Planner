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
   private String time;
   private PlaceDTO place;
   
   
   public PlanDTO() {   
   }
   
   
   public PlanDTO(String date, String time, PlaceDTO place) {
      this.date = date;
      this.time = time;
      this.place = place;
   }
   
   @Override
   public String toString() {
      return "PlanDTO [date=" + date + ", time=" + time + ", place=" + place + "]";
   }
}