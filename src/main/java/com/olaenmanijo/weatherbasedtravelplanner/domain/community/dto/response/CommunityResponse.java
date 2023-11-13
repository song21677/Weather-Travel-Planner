package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.olaenmanijo.weatherbasedtravelplanner.global.file.dto.response.TravelReviewFileResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class CommunityResponse {

	Long travelReviewNo;
	Long travelPlanNo;
	Long memberNo;
	String plannerReviewTitle;
	String plannerReviewContent;
	String writeDate;
	Long hit;
	Long recommend;
	Integer delYn;
	
	String memberName;
	Long dateTem;
	
	List<TravelReviewFileResponse> travelReviewFileResponse = new ArrayList<>();

	public void setTravelReviewFileResponse(List<TravelReviewFileResponse> travelReviewFileResponse) {
		this.travelReviewFileResponse = travelReviewFileResponse;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setDateTem(Long dateTem) {
		this.dateTem = dateTem;
	}
	
	
	
}
