package com.olaenmanijo.weatherbasedtravelplanner.domain.community.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.CommunityRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.PlaceReviewRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityDetailResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityPlanListResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.PlaceReviewResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.mapper.CommunityMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityService {

	private final CommunityMapper communityMapper;

	/**
	 * 여행기 저장 - 여행기 작성페이지
	 * 
	 * @param params - 여행기 정보
	 * @return Generated PK
	 */
	@Transactional
	public Long travelReviewSave(final CommunityRequest params) {
		communityMapper.travelReviewSave(params);
		return params.getTravelReviewNo();
	}

	/**
	 * 여행지 리뷰 저장 - 여행기 작성페이지
	 * 
	 * @param params - 여행지 리뷰 정보
	 * @return Generated PK
	 */
	@Transactional
	public Long placeReviewWrite(final PlaceReviewRequest params) {
		communityMapper.placeReviewWrite(params);
		return params.getPlaceReviewNo();
	}

	/**
	 * 여행기 수정 - 여행기 작성페이지
	 * 
	 * @param params - 여행기 정보
	 * @return PK
	 */
	@Transactional
	public Long travelReviewUpdate(final CommunityRequest params) {
		communityMapper.travelReviewUpdate(params);
		return params.getTravelReviewNo();
	}

	/**
	 * 여행지 리뷰 수정 - 여행기 작성페이지, 마이페이지
	 * 
	 * @param params - 여행지 리뷰 정보
	 * @return PK
	 */
	@Transactional
	public Long placeReviewUpdate(final PlaceReviewRequest params) {
		communityMapper.placeReviewUpdate(params);
		return params.getPlaceReviewNo();
	}

	/**
	 * 게시글 상세정보 조회 - 여행기 상세보기페이지
	 * 
	 * @param travelReviewNo - PK
	 * @return 게시글 상세정보(title,img,content)
	 */
	public CommunityResponse travelReviewfindById(final Long travelReviewNo) {
		return communityMapper.travelReviewFindById(travelReviewNo);
	}

	/**
	 * 게시글 상세정보 조회 - 여행기 상세보기페이지
	 * 
	 * @param travelPlanNo - PK
	 * @return 여행일정 상세정보(여행일정으로 블럭정보 리스트)
	 */
	public List<CommunityDetailResponse> travelDetailPlanFindByTravelNo(Long travelPlanNo) {
		return communityMapper.travelDetailPlanFindByTravelNo(travelPlanNo);
	}

	public Map<String, List<CommunityDetailResponse>> detailFindByTravelNo(Long travelPlanNo) {
		List<CommunityDetailResponse> details = communityMapper.travelDetailPlanFindByTravelNo(travelPlanNo);
		Map<String, List<CommunityDetailResponse>> groupedData = new LinkedHashMap<>();
		for (CommunityDetailResponse detail : details) {
			String detailPlanYMD = detail.getDetailPlanYMD();
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

			System.out.println("detailPlanYMD: " + detailPlanYMD);
			for (CommunityDetailResponse content : groupedContents) {
				System.out.println(content);
			}
		}

		return groupedData;

	}

	/**
	 * 게시글 상세정보 조회 - 여행기 상세보기페이지
	 * 
	 * @param travelPlanNo - PK
	 * @return 게시글 상세정보(여행일정으로 여행기불러오기)
	 */
	public CommunityResponse travelReviewFindByTravelNo(Long travelPlanNo) {
		return communityMapper.travelReviewFindByTravelNo(travelPlanNo);
	}

	/**
	 * 여행일정 리스트 조회 - 여행기 작성페이지
	 * 
	 * @param memberNo
	 * @return 여행일정 리스트
	 */
	public List<CommunityPlanListResponse> travelPlanFindByMemberNo(Long memberNo) {
		return communityMapper.travelPlanFindByMemberNo(memberNo);
	}

	/**
	 * 여행지 리뷰 여행블럭번호로 조회 - 여행기 작성페이지
	 * 
	 * @param detailPlanNo
	 * @return 여행지 리뷰 정보 
	 */
	public PlaceReviewResponse placeReviewFindByDetailNo(Long detailPlanNo) {
		return communityMapper.placeReviewFindByDetailNo(detailPlanNo);
	}

	/**
	 * 여행지 리뷰 멤버번호로 조회 - 마이페이지
	 * 
	 * @param memberNo
	 * @return 여행블럭정보 리스트
	 */
	public List<PlaceReviewResponse> placeReviewFindByMemberNo(Long memberNo) {
		return communityMapper.placeReviewFindByMemberNo(memberNo);
	}

	/**
	 * 여행지 리뷰 여행지번호로 조회 - 여행정보
	 * 
	 * @param placeNo
	 * @return 여행블럭정보 리스트
	 */
	public List<PlaceReviewResponse> placeReviewFindByPlaceNo(Long placeNo) {
		return communityMapper.placeReviewFindByPlaceNo(placeNo);
	}

	/**
	 * 게시글 리스트 조회 - 여행기 메인페이지
	 * 
	 * @return 게시글 리스트
	 */
	public List<CommunityResponse> travelReviewFindAll() {
		return communityMapper.travelReviewFindAll();
	}

	/**
	 * 여행지 리뷰 수 카운팅 (등록 수정 여부 확인)
	 * 
	 * @param detailPlanNo - UK
	 * @return 여행지 리뷰 수
	 */
	public Long countBydetailPlanNo(final Long detailPlanNo) {
		return communityMapper.countBydetailPlanNo(detailPlanNo);
	}
}
