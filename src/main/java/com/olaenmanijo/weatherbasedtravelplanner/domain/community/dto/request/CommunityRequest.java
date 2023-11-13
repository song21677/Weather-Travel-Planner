package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class CommunityRequest {

	Long travelReviewNo;
	Long travelPlanNo;
	Long memberNo;
	String plannerReviewTitle;
	String plannerReviewContent;

}
