package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request;

import org.apache.ibatis.type.Alias;

@Alias("PlaceReviewRegistRequest")
public class PlaceReviewRegistRequest {

	int DETAIL_PLAN_NO;
	int MEMBER_NO;
	int PLACE_NO;
	String PLACE_REVIEW_IMG;
	String PLACE_REVIEW_CONTENT;
	long PLACE_REVIEW_SCORE;

	public PlaceReviewRegistRequest() {
	}

	public PlaceReviewRegistRequest(int dETAIL_PLAN_NO, int mEMBER_NO, int pLACE_NO,
			String pLACE_REVIEW_IMG, String pLACE_REVIEW_CONTENT, long pLACE_REVIEW_SCORE) {
		DETAIL_PLAN_NO = dETAIL_PLAN_NO;
		MEMBER_NO = mEMBER_NO;
		PLACE_NO = pLACE_NO;
		PLACE_REVIEW_IMG = pLACE_REVIEW_IMG;
		PLACE_REVIEW_CONTENT = pLACE_REVIEW_CONTENT;
		PLACE_REVIEW_SCORE = pLACE_REVIEW_SCORE;
	}

	public int getDETAIL_PLAN_NO() {
		return DETAIL_PLAN_NO;
	}

	public void setDETAIL_PLAN_NO(int dETAIL_PLAN_NO) {
		DETAIL_PLAN_NO = dETAIL_PLAN_NO;
	}

	public int getMEMBER_NO() {
		return MEMBER_NO;
	}

	public void setMEMBER_NO(int mEMBER_NO) {
		MEMBER_NO = mEMBER_NO;
	}

	public int getPLACE_NO() {
		return PLACE_NO;
	}

	public void setPLACE_NO(int pLACE_NO) {
		PLACE_NO = pLACE_NO;
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
		return "PlaceReviewRegistRequest [DETAIL_PLAN_NO=" + DETAIL_PLAN_NO + ", MEMBER_NO=" + MEMBER_NO + ", PLACE_NO="
				+ PLACE_NO + ", PLACE_REVIEW_IMG=" + PLACE_REVIEW_IMG + ", PLACE_REVIEW_CONTENT=" + PLACE_REVIEW_CONTENT
				+ ", PLACE_REVIEW_SCORE=" + PLACE_REVIEW_SCORE + "]";
	}

	

}
