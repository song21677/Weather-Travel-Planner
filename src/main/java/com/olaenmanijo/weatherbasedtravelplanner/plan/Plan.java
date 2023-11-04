package com.olaenmanijo.weatherbasedtravelplanner.plan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Component
@SessionScope
@Getter
@Setter
public class Plan {

	String daterange;
	String title;
	List<PlanDTO> planDTOs = new ArrayList<>();
	
	public Plan() {
	}
	
	public void add(PlanDTO planDTO) {
		planDTOs.add(planDTO);
	}
}
