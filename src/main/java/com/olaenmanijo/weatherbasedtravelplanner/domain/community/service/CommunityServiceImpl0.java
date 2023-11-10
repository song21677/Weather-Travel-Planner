package com.olaenmanijo.weatherbasedtravelplanner.domain.community.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dao.CommunityDAO;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.CommunityModifyRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.CommunityRegistRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.PlaceReviewModifyRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.PlaceReviewRegistRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityDetailResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityListResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityPlannerListResponse;

@Service
public class CommunityServiceImpl implements CommunityService {

	@Autowired
	CommunityDAO dao;

	@Override
	@Transactional
	public int communityWrite(CommunityRegistRequest community) {
		return dao.communityWrite(community);
	}

	@Override
	public CommunityListResponse selectCommunity(int travelReviewNo) {
		return dao.selectCommunity(travelReviewNo);
	}

	@Override
	public Map<String, List<CommunityDetailResponse>> selectPlannerDetail(int travelPlanNo) {
		List<CommunityDetailResponse> details = dao.selectPlannerDetail(travelPlanNo);
		Map<String, List<CommunityDetailResponse>> groupedData = new LinkedHashMap<>();

		for (CommunityDetailResponse detail : details) {
			String detailPlanYMD = detail.getDETAIL_PLAN_YMD();
			if (detailPlanYMD != null) {
				List<CommunityDetailResponse> dateList = groupedData.computeIfAbsent(detailPlanYMD,
						k -> new ArrayList<>());
				dateList.add(detail);
			}
		}

		// 출력
		for (Map.Entry<String, List<CommunityDetailResponse>> entry : groupedData.entrySet()) {
			String detailPlanYMD = entry.getKey();
			List<CommunityDetailResponse> groupedContents = entry.getValue();

			System.out.println("DETAIL_PLAN_YMD: " + detailPlanYMD);
			for (CommunityDetailResponse content : groupedContents) {
				System.out.println(content);
			}
		}

		return groupedData;
	}

	@Override
	@Transactional
	public int communityUpdate(CommunityModifyRequest community) {
		return dao.communityUpdate(community);
	}

	@Override
	public int communityDelete(int travelPlanNo) {
		return dao.communityDelete(travelPlanNo);
	}

	@Override
	public List<CommunityListResponse> communityList() {
		return dao.communityList();
	}

	@Override
	@Transactional
	public int placeReviewWrite(PlaceReviewRegistRequest placeReview) {
		return dao.placeReviewWrite(placeReview);
	}

	@Override
	@Transactional
	public int placeReviewUpdate(PlaceReviewModifyRequest placeReview) {
		return dao.placeReviewUpdate(placeReview);
	}

	@Override
	public List<CommunityPlannerListResponse> selectPlannerList(int memberNo) {
		return dao.selectPlannerList(memberNo);
	}

	@Override
	public CommunityListResponse selCommuTravelNo(int travelPlanNo) {
		return dao.selCommuTravelNo(travelPlanNo);
	}

}
