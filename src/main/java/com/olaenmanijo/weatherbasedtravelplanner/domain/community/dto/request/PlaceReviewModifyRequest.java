package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request;

import org.apache.ibatis.type.Alias;

@Alias("PlaceReviewModifyRequest")
public class PlaceReviewModifyRequest {

	int PLACE_REVIEW_NO;
	String PLACE_REVIEW_IMG;
	String PLACE_REVIEW_CONTENT;
	long PLACE_REVIEW_SCORE;

	public PlaceReviewModifyRequest() {
	}

	public PlaceReviewModifyRequest(int pLACE_REVIEW_NO, String pLACE_REVIEW_IMG, String pLACE_REVIEW_CONTENT,
			long pLACE_REVIEW_SCORE) {
		PLACE_REVIEW_NO = pLACE_REVIEW_NO;
		PLACE_REVIEW_IMG = pLACE_REVIEW_IMG;
		PLACE_REVIEW_CONTENT = pLACE_REVIEW_CONTENT;
		PLACE_REVIEW_SCORE = pLACE_REVIEW_SCORE;
	}

	public int getPLACE_REVIEW_NO() {
		return PLACE_REVIEW_NO;
	}

	public void setPLACE_REVIEW_NO(int pLACE_REVIEW_NO) {
		PLACE_REVIEW_NO = pLACE_REVIEW_NO;
	}

	public String getPLACE_REVIEW_IMG() {
		return PLACE_REVIEW_IMG;
	}

	public void setPLACE_REVIEW_IMG(String pLACE_REVIEW_IMG) {
		PLACE_REVIEW_IMG = pLACE_REVIEW_IMG;
	}

	public String getPLACE_REVIEW_CONTENT() {
		return PLACE_REVIEW_CONTENT;
	}

	public void setPLACE_REVIEW_CONTENT(String pLACE_REVIEW_CONTENT) {
		PLACE_REVIEW_CONTENT = pLACE_REVIEW_CONTENT;
	}

	public long getPLACE_REVIEW_SCORE() {
		return PLACE_REVIEW_SCORE;
	}

	public void setPLACE_REVIEW_SCORE(long pLACE_REVIEW_SCORE) {
		PLACE_REVIEW_SCORE = pLACE_REVIEW_SCORE;
	}

	@Override
	public String toString() {
		return "PlaceReviewModifyRequest [PLACE_REVIEW_NO=" + PLACE_REVIEW_NO + ", PLACE_REVIEW_IMG=" + PLACE_REVIEW_IMG
				+ ", PLACE_REVIEW_CONTENT=" + PLACE_REVIEW_CONTENT + ", PLACE_REVIEW_SCORE=" + PLACE_REVIEW_SCORE + "]";
	}

}
