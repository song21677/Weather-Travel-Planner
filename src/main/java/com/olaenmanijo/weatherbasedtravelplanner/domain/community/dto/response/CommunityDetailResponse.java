package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response;

import org.apache.ibatis.type.Alias;

@Alias("CommunityDetailResponse")
public class CommunityDetailResponse {
	int TRAVEL_PLAN_NO;
	int MEMBER_NO;
	String TRAVEL_PLAN_TITLE;
	String START_YMD;
	String END_YMD;

	int DETAIL_PLAN_NO;
	String DETAIL_PLAN_YMD;
	String DETAIL_PLAN_HOUR;
	String DETAIL_PLAN_DATE;
	String COLOR;
	String BLOCK_ORD;

	int PLACE_NO;
	String PLACE_CATEGORY;
	String PLACE_NAME;
	String ROAD_NAME_ADR;
	String LOT_NUMBER_ADR;
	String LONGITUDE;
	String LATITUDE;
	long SCORE;

	int PLACE_REVIEW_NO;
	String PLACE_REVIEW_IMG;
	String PLACE_REVIEW_CONTENT;
	long PLACE_REVIEW_SCORE;
	String WRITE_DATE;

	public CommunityDetailResponse() {
	}

	public CommunityDetailResponse(int tRAVEL_PLAN_NO, int mEMBER_NO, String tRAVEL_PLAN_TITLE, String sTART_YMD,
			String eND_YMD, int dETAIL_PLAN_NO, String dETAIL_PLAN_YMD, String dETAIL_PLAN_HOUR,
			String dETAIL_PLAN_DATE, String cOLOR, String bLOCK_ORD, int pLACE_NO, String pLACE_CATEGORY,
			String pLACE_NAME, String rOAD_NAME_ADR, String lOT_NUMBER_ADR, String lONGITUDE, String lATITUDE,
			long sCORE, int pLACE_REVIEW_NO, String pLACE_REVIEW_IMG, String pLACE_REVIEW_CONTENT,
			long pLACE_REVIEW_SCORE, String wRITE_DATE) {
		TRAVEL_PLAN_NO = tRAVEL_PLAN_NO;
		MEMBER_NO = mEMBER_NO;
		TRAVEL_PLAN_TITLE = tRAVEL_PLAN_TITLE;
		START_YMD = sTART_YMD;
		END_YMD = eND_YMD;
		DETAIL_PLAN_NO = dETAIL_PLAN_NO;
		DETAIL_PLAN_YMD = dETAIL_PLAN_YMD;
		DETAIL_PLAN_HOUR = dETAIL_PLAN_HOUR;
		DETAIL_PLAN_DATE = dETAIL_PLAN_DATE;
		COLOR = cOLOR;
		BLOCK_ORD = bLOCK_ORD;
		PLACE_NO = pLACE_NO;
		PLACE_CATEGORY = pLACE_CATEGORY;
		PLACE_NAME = pLACE_NAME;
		ROAD_NAME_ADR = rOAD_NAME_ADR;
		LOT_NUMBER_ADR = lOT_NUMBER_ADR;
		LONGITUDE = lONGITUDE;
		LATITUDE = lATITUDE;
		SCORE = sCORE;
		PLACE_REVIEW_NO = pLACE_REVIEW_NO;
		PLACE_REVIEW_IMG = pLACE_REVIEW_IMG;
		PLACE_REVIEW_CONTENT = pLACE_REVIEW_CONTENT;
		PLACE_REVIEW_SCORE = pLACE_REVIEW_SCORE;
		WRITE_DATE = wRITE_DATE;
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

	public int getDETAIL_PLAN_NO() {
		return DETAIL_PLAN_NO;
	}

	public void setDETAIL_PLAN_NO(int dETAIL_PLAN_NO) {
		DETAIL_PLAN_NO = dETAIL_PLAN_NO;
	}

	public String getDETAIL_PLAN_YMD() {
		return DETAIL_PLAN_YMD;
	}

	public void setDETAIL_PLAN_YMD(String dETAIL_PLAN_YMD) {
		DETAIL_PLAN_YMD = dETAIL_PLAN_YMD;
	}

	public String getDETAIL_PLAN_HOUR() {
		return DETAIL_PLAN_HOUR;
	}

	public void setDETAIL_PLAN_HOUR(String dETAIL_PLAN_HOUR) {
		DETAIL_PLAN_HOUR = dETAIL_PLAN_HOUR;
	}

	public String getDETAIL_PLAN_DATE() {
		return DETAIL_PLAN_DATE;
	}

	public void setDETAIL_PLAN_DATE(String dETAIL_PLAN_DATE) {
		DETAIL_PLAN_DATE = dETAIL_PLAN_DATE;
	}

	public String getCOLOR() {
		return COLOR;
	}

	public void setCOLOR(String cOLOR) {
		COLOR = cOLOR;
	}

	public String getBLOCK_ORD() {
		return BLOCK_ORD;
	}

	public void setBLOCK_ORD(String bLOCK_ORD) {
		BLOCK_ORD = bLOCK_ORD;
	}

	public int getPLACE_NO() {
		return PLACE_NO;
	}

	public void setPLACE_NO(int pLACE_NO) {
		PLACE_NO = pLACE_NO;
	}

	public String getPLACE_CATEGORY() {
		return PLACE_CATEGORY;
	}

	public void setPLACE_CATEGORY(String pLACE_CATEGORY) {
		PLACE_CATEGORY = pLACE_CATEGORY;
	}

	public String getPLACE_NAME() {
		return PLACE_NAME;
	}

	public void setPLACE_NAME(String pLACE_NAME) {
		PLACE_NAME = pLACE_NAME;
	}

	public String getROAD_NAME_ADR() {
		return ROAD_NAME_ADR;
	}

	public void setROAD_NAME_ADR(String rOAD_NAME_ADR) {
		ROAD_NAME_ADR = rOAD_NAME_ADR;
	}

	public String getLOT_NUMBER_ADR() {
		return LOT_NUMBER_ADR;
	}

	public void setLOT_NUMBER_ADR(String lOT_NUMBER_ADR) {
		LOT_NUMBER_ADR = lOT_NUMBER_ADR;
	}

	public String getLONGITUDE() {
		return LONGITUDE;
	}

	public void setLONGITUDE(String lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}

	public String getLATITUDE() {
		return LATITUDE;
	}

	public void setLATITUDE(String lATITUDE) {
		LATITUDE = lATITUDE;
	}

	public long getSCORE() {
		return SCORE;
	}

	public void setSCORE(long sCORE) {
		SCORE = sCORE;
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

	public String getWRITE_DATE() {
		return WRITE_DATE;
	}

	public void setWRITE_DATE(String wRITE_DATE) {
		WRITE_DATE = wRITE_DATE;
	}

	@Override
	public String toString() {
		return "CommunityDetailResponse [TRAVEL_PLAN_NO=" + TRAVEL_PLAN_NO + ", MEMBER_NO=" + MEMBER_NO
				+ ", TRAVEL_PLAN_TITLE=" + TRAVEL_PLAN_TITLE + ", START_YMD=" + START_YMD + ", END_YMD=" + END_YMD
				+ ", DETAIL_PLAN_NO=" + DETAIL_PLAN_NO + ", DETAIL_PLAN_YMD=" + DETAIL_PLAN_YMD + ", DETAIL_PLAN_HOUR="
				+ DETAIL_PLAN_HOUR + ", DETAIL_PLAN_DATE=" + DETAIL_PLAN_DATE + ", COLOR=" + COLOR + ", BLOCK_ORD="
				+ BLOCK_ORD + ", PLACE_NO=" + PLACE_NO + ", PLACE_CATEGORY=" + PLACE_CATEGORY + ", PLACE_NAME="
				+ PLACE_NAME + ", ROAD_NAME_ADR=" + ROAD_NAME_ADR + ", LOT_NUMBER_ADR=" + LOT_NUMBER_ADR
				+ ", LONGITUDE=" + LONGITUDE + ", LATITUDE=" + LATITUDE + ", SCORE=" + SCORE + ", PLACE_REVIEW_NO="
				+ PLACE_REVIEW_NO + ", PLACE_REVIEW_IMG=" + PLACE_REVIEW_IMG + ", PLACE_REVIEW_CONTENT="
				+ PLACE_REVIEW_CONTENT + ", PLACE_REVIEW_SCORE=" + PLACE_REVIEW_SCORE + ", WRITE_DATE=" + WRITE_DATE
				+ "]";
	}

}
