package com.olaenmanijo.weatherbasedtravelplanner.MainPage;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("GetRecommendDTO")
@Getter
@Setter
@ToString
public class GetRecommendDTO {
	String TRAVEL_REVIEW_NO;
	String PLANNER_REVIEW_TITLE;
	String NICKNAME;
	String SAVE_NAME;
}
