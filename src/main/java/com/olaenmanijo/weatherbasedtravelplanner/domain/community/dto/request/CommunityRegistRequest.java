package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request;

import org.apache.ibatis.type.Alias;

@Alias("CommunityRegistRequest")
public class CommunityRegistRequest {
	int TRAVEL_PLAN_NO;
	int MEMBER_NO;
	String PLANNER_REVIEW_TITLE;
	String PLANNER_REVIEW_IMAGE;
	String PLANNER_REVIEW_CONTENT;

	public CommunityRegistRequest() {
	}

	public CommunityRegistRequest(int tRAVEL_PLAN_NO, int mEMBER_NO, String pLANNER_REVIEW_TITLE,
			String pLANNER_REVIEW_IMAGE, String pLANNER_REVIEW_CONTENT) {
		TRAVEL_PLAN_NO = tRAVEL_PLAN_NO;
		MEMBER_NO = mEMBER_NO;
		PLANNER_REVIEW_TITLE = pLANNER_REVIEW_TITLE;
		PLANNER_REVIEW_IMAGE = pLANNER_REVIEW_IMAGE;
		PLANNER_REVIEW_CONTENT = pLANNER_REVIEW_CONTENT;
	}

	public int getTRAVEL_PLAN_NO() {
		return TRAVEL_PLAN_NO;
	}

	public void setTRAVEL_PLAN_NO(int tRAVEL_PLAN_NO) {
		TRAVEL_PLAN_NO = tRAVEL_PLAN_NO;
	}

	public int getMEMBER_NO() {
		return MEMBER_NO;
	}

	public void setMEMBER_NO(int mEMBER_NO) {
		MEMBER_NO = mEMBER_NO;
	}

	public String getPLANNER_REVIEW_TITLE() {
		return PLANNER_REVIEW_TITLE;
	}

	public void setPLANNER_REVIEW_TITLE(String pLANNER_REVIEW_TITLE) {
		PLANNER_REVIEW_TITLE = pLANNER_REVIEW_TITLE;
	}

	public String getPLANNER_REVIEW_IMAGE() {
		return PLANNER_REVIEW_IMAGE;
	}

	public void setPLANNER_REVIEW_IMAGE(String pLANNER_REVIEW_IMAGE) {
		PLANNER_REVIEW_IMAGE = pLANNER_REVIEW_IMAGE;
	}

	public String getPLANNER_REVIEW_CONTENT() {
		return PLANNER_REVIEW_CONTENT;
	}

	public void setPLANNER_REVIEW_CONTENT(String pLANNER_REVIEW_CONTENT) {
		PLANNER_REVIEW_CONTENT = pLANNER_REVIEW_CONTENT;
	}

	@Override
	public String toString() {
		return "CommunityRegistRequest [TRAVEL_PLAN_NO=" + TRAVEL_PLAN_NO + ", MEMBER_NO=" + MEMBER_NO
				+ ", PLANNER_REVIEW_TITLE=" + PLANNER_REVIEW_TITLE + ", PLANNER_REVIEW_IMAGE=" + PLANNER_REVIEW_IMAGE
				+ ", PLANNER_REVIEW_CONTENT=" + PLANNER_REVIEW_CONTENT + "]";
	}

}
