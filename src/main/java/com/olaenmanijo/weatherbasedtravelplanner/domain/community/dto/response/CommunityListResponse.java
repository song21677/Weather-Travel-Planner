package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response;

import org.apache.ibatis.type.Alias;

@Alias("CommunityListResponse")
public class CommunityListResponse {
	int TRAVEL_REVIEW_NO;
	int TRAVEL_PLAN_NO;
	int MEMBER_NO;
	String PLANNER_REVIEW_TITLE;
	String PLANNER_REVIEW_IMAGE;
	String PLANNER_REVIEW_CONTENT;
	String WRITE_DATE;
	int HIT;
	int RECOMMEND;

	public CommunityListResponse() {
	}

	public CommunityListResponse(int tRAVEL_REVIEW_NO, int tRAVEL_PLAN_NO, int mEMBER_NO, String pLANNER_REVIEW_TITLE,
			String pLANNER_REVIEW_IMAGE, String pLANNER_REVIEW_CONTENT, String wRITE_DATE, int hIT, int rECOMMEND) {
		TRAVEL_REVIEW_NO = tRAVEL_REVIEW_NO;
		TRAVEL_PLAN_NO = tRAVEL_PLAN_NO;
		MEMBER_NO = mEMBER_NO;
		PLANNER_REVIEW_TITLE = pLANNER_REVIEW_TITLE;
		PLANNER_REVIEW_IMAGE = pLANNER_REVIEW_IMAGE;
		PLANNER_REVIEW_CONTENT = pLANNER_REVIEW_CONTENT;
		WRITE_DATE = wRITE_DATE;
		HIT = hIT;
		RECOMMEND = rECOMMEND;
	}

	public int getTRAVEL_REVIEW_NO() {
		return TRAVEL_REVIEW_NO;
	}

	public void setTRAVEL_REVIEW_NO(int tRAVEL_REVIEW_NO) {
		TRAVEL_REVIEW_NO = tRAVEL_REVIEW_NO;
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

	public String getWRITE_DATE() {
		return WRITE_DATE;
	}

	public void setWRITE_DATE(String wRITE_DATE) {
		WRITE_DATE = wRITE_DATE;
	}

	public int getHIT() {
		return HIT;
	}

	public void setHIT(int hIT) {
		HIT = hIT;
	}

	public int getRECOMMEND() {
		return RECOMMEND;
	}

	public void setRECOMMEND(int rECOMMEND) {
		RECOMMEND = rECOMMEND;
	}

	@Override
	public String toString() {
		return "CommunityListResponse [TRAVEL_REVIEW_NO=" + TRAVEL_REVIEW_NO + ", TRAVEL_PLAN_NO=" + TRAVEL_PLAN_NO
				+ ", MEMBER_NO=" + MEMBER_NO + ", PLANNER_REVIEW_TITLE=" + PLANNER_REVIEW_TITLE
				+ ", PLANNER_REVIEW_IMAGE=" + PLANNER_REVIEW_IMAGE + ", PLANNER_REVIEW_CONTENT="
				+ PLANNER_REVIEW_CONTENT + ", WRITE_DATE=" + WRITE_DATE + ", HIT=" + HIT + ", RECOMMEND=" + RECOMMEND
				+ "]";
	}

}
