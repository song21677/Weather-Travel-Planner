package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request;

import org.apache.ibatis.type.Alias;

@Alias("CommunityModifyRequest")
public class CommunityModifyRequest {
	int TRAVEL_REVIEW_NO;
	String PLANNER_REVIEW_TITLE;
	String PLANNER_REVIEW_IMAGE;
	String PLANNER_REVIEW_CONTENT;

	public CommunityModifyRequest() {
	}

	public CommunityModifyRequest(int tRAVEL_REVIEW_NO, String pLANNER_REVIEW_TITLE, String pLANNER_REVIEW_IMAGE,
			String pLANNER_REVIEW_CONTENT) {
		TRAVEL_REVIEW_NO = tRAVEL_REVIEW_NO;
		PLANNER_REVIEW_TITLE = pLANNER_REVIEW_TITLE;
		PLANNER_REVIEW_IMAGE = pLANNER_REVIEW_IMAGE;
		PLANNER_REVIEW_CONTENT = pLANNER_REVIEW_CONTENT;
	}

	public int getTRAVEL_REVIEW_NO() {
		return TRAVEL_REVIEW_NO;
	}

	public void setTRAVEL_REVIEW_NO(int tRAVEL_REVIEW_NO) {
		TRAVEL_REVIEW_NO = tRAVEL_REVIEW_NO;
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
		return "CommunityModifyRequest [TRAVEL_REVIEW_NO=" + TRAVEL_REVIEW_NO + ", PLANNER_REVIEW_TITLE="
				+ PLANNER_REVIEW_TITLE + ", PLANNER_REVIEW_IMAGE=" + PLANNER_REVIEW_IMAGE + ", PLANNER_REVIEW_CONTENT="
				+ PLANNER_REVIEW_CONTENT + "]";
	}

}
