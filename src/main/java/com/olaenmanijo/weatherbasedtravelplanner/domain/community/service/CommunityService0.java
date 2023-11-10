package com.olaenmanijo.weatherbasedtravelplanner.domain.community.service;

import java.util.List;
import java.util.Map;

import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.CommunityModifyRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.CommunityRegistRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.PlaceReviewModifyRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.PlaceReviewRegistRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityDetailResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityListResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityPlannerListResponse;

public interface CommunityService {

	public int communityWrite(CommunityRegistRequest community);

	public CommunityListResponse selectCommunity(int travelReviewNo);

	public Map<String, List<CommunityDetailResponse>> selectPlannerDetail(int travelPlanNo);

	public int communityUpdate(CommunityModifyRequest community);

	public int communityDelete(int travelPlanNo);

	public List<CommunityListResponse> communityList();

	public int placeReviewWrite(PlaceReviewRegistRequest placeReview);

	public int placeReviewUpdate(PlaceReviewModifyRequest placeReview);

	public List<CommunityPlannerListResponse> selectPlannerList(int memberNo);
	
	public CommunityListResponse selCommuTravelNo(int travelPlanNo);

}
