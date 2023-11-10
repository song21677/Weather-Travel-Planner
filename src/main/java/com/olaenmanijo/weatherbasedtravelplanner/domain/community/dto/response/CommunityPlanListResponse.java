package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response;

import org.apache.ibatis.type.Alias;

@Alias("CommunityPlannerListResponse")
public class CommunityPlannerListResponse {
	int TRAVEL_PLAN_NO;
	int MEMBER_NO;
	String TRAVEL_PLAN_TITLE;
	String START_YMD;
	String END_YMD;

	public CommunityPlannerListResponse() {
	}

	public CommunityPlannerListResponse(int tRAVEL_PLAN_NO, int mEMBER_NO, String tRAVEL_PLAN_TITLE, String sTART_YMD,
			String eND_YMD) {
		TRAVEL_PLAN_NO = tRAVEL_PLAN_NO;
		MEMBER_NO = mEMBER_NO;
		TRAVEL_PLAN_TITLE = tRAVEL_PLAN_TITLE;
		START_YMD = sTART_YMD;
		END_YMD = eND_YMD;
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

	public String getTRAVEL_PLAN_TITLE() {
		return TRAVEL_PLAN_TITLE;
	}

	public void setTRAVEL_PLAN_TITLE(String tRAVEL_PLAN_TITLE) {
		TRAVEL_PLAN_TITLE = tRAVEL_PLAN_TITLE;
	}

	public String getSTART_YMD() {
		return START_YMD;
	}

	public void setSTART_YMD(String sTART_YMD) {
		START_YMD = sTART_YMD;
	}

	public String getEND_YMD() {
		return END_YMD;
	}

	public void setEND_YMD(String eND_YMD) {
		END_YMD = eND_YMD;
	}

	@Override
	public String toString() {
		return "CmntPlannerListDTO [TRAVEL_PLAN_NO=" + TRAVEL_PLAN_NO + ", MEMBER_NO=" + MEMBER_NO
				+ ", TRAVEL_PLAN_TITLE=" + TRAVEL_PLAN_TITLE + ", START_YMD=" + START_YMD + ", END_YMD=" + END_YMD
				+ "]";
	}

}
