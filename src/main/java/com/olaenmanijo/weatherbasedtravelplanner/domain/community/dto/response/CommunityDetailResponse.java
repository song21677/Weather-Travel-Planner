package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CommunityDetailResponse {

	// TravelPlan
	Long travelPlanNo;
	Long memberNo;
	String travelPlanTitle;
	String startYMD;
	String endYMD;

	// DetailPlan
	Long detailPlanNo;
	String detailPlanYMD;
	String detailPlanHour;
	String detailPlanHourEnd;
	String color;

	// Place
	Long placeNo;
	String placeCategory;
	String placeName;
	String roadNameAdr;
	String lotNumberAdr;
	Long score;
	String area;
	String longitude;
	String latitude;

	// PlaceReview
	Long placeReviewNo;
	String placeReviewImg;
	String placeReviewContent;
	Long placeReviewScore;
	String writeDate;

}
