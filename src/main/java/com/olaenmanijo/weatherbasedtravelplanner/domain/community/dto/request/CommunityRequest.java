package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class CommunityRequest {
	// 사용자가 여행기 작성시 입력하는 항목
	/*- <<TRAVEL_REVIEW_TB>>
		TRAVEL_REVIEW_NO - 수정 시 매핑
		TRAVEL_PLAN_NO - 여행일정 선택 시 입력
		MEMBER_NO - 글 작성 시 입력
		PLANNER_REVIEW_TITLE - 사용자 직접 입력
		PLANNER_REVIEW_IMAGE - 사용자 직접 입력
		PLANNER_REVIEW_CONTENT - 사용자 직접 입력
		WRITE_DATE 
		HIT
		RECOMMEND
		DEL_YN
	*/
	Long travelReviewNo;
	Long travelPlanNo;
	Long memberNo;
	String plannerReviewTitle;
	String plannerReviewImage;
	String plannerReviewContent;

	

}
