package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Alias("CommunityRegistRequest")
public class CommunityRegistRequest {
	Integer travelPlanNo;
	Integer memberNo;
	String plannerReviewTitle;
	String plannerReviewImage;
	String plannerReviewContent;

	public CommunityRegistRequest() {
	}

	

}
