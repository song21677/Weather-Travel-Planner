package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response;


import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CommunityPlanListResponse {
	Long travelPlanNo;
	Long memberNo;
	String travelPlanTitle;
	String startYMD;
	String endYMD;
}
