package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class PlaceReviewResponse {

	Long placeReviewNo;
	Long detailPlanNo;
	Long memberNo;
	Long placeNo;
	String placeReviewImg;
	String placeReviewContent;
	Long placeReviewScore;
	String writeDate;

}
