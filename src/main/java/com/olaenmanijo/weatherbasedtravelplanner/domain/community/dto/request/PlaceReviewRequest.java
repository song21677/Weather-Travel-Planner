package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class PlaceReviewRequest {

	/*-
	private Long placeReviewNo; // 시퀸스로 입력
    private Long detailPlanNo;
    private Long memberNo;
    private Long placeNo;
    private String placeReviewImg;
    private String placeReviewContent;
    private Long placeReviewScore;
    private Date writeDate; // 자동입력
	*/
	Long placeReviewNo;
	Long detailPlanNo;
	Long memberNo;
	Long placeNo;
	String placeReviewImg;
	String placeReviewContent;
	Long placeReviewScore;
	

}
