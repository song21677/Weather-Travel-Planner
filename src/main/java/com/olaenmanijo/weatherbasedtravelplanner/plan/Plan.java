package com.olaenmanijo.weatherbasedtravelplanner.plan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain.Place;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@SessionScope
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Plan implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty
	String daterange;
	@JsonProperty
	String title;
	@JsonProperty
	List<Item> places = new ArrayList<>();
	
	public Plan() {
	}
	
	public void add(Item place) {
		places.add(place);
	}
}
