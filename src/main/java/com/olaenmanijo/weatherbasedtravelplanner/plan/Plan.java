package com.olaenmanijo.weatherbasedtravelplanner.plan;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Plan {

	String daterange;
	String title;
	List<PlanDTO> planDTOs;
}
