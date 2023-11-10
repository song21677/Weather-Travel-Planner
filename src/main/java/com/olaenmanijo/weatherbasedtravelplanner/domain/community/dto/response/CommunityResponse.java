package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CommunityResponse {

	Long travelReviewNo;
	Long travelPlanNo;
	Long memberNo;
	String plannerReviewTitle;
	String plannerReviewImage;
	String plannerReviewContent;
	String writeDate;
	Long hit;
	Long recommend;
	Integer delYn;

}
