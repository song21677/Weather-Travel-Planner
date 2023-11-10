package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.CommunityModifyRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.CommunityRegistRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.PlaceReviewModifyRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.PlaceReviewRegistRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityDetailResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityListResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityPlannerListResponse;

@Repository
public class CommunityDAO {
	@Autowired
	SqlSessionTemplate session;

	// 게시글 저장
	public int communityWrite(CommunityRegistRequest community) {
		return session.insert("TravelReviewMapper.communityWrite", community);
	}

	// 조회수 증가
	public int readHit(int travelReviewNo) {
		return session.update("TravelReviewMapper.readHit", travelReviewNo);
	}

	// 게시글 상세보기
	public CommunityListResponse selectCommunity(int travelReviewNo) {
		// 조회수 증가 메서드 호출
		int num = readHit(travelReviewNo);
		return session.selectOne("TravelReviewMapper.selectCommunity", travelReviewNo);
	}

	// 게시글 여행일정으로 조회
	public CommunityListResponse selCommuTravelNo(int travelPlanNo) {
		return session.selectOne("TravelReviewMapper.selCommuTravelNo", travelPlanNo);
	}

	// 여행일정 리스트 보기
	public List<CommunityPlannerListResponse> selectPlannerList(int memberNo) {
		return session.selectList("TravelPlanMapper.selectPlannerList", memberNo);
	}

	// 여행 정보 자세히 보기
	public List<CommunityDetailResponse> selectPlannerDetail(int travelPlanNo) {
		return session.selectList("DetailPlanMapper.selectPlannerDetail", travelPlanNo);
	}

	// 게시글 수정
	public int communityUpdate(CommunityModifyRequest community) {
		return session.update("TravelReviewMapper.communityUpdate", community);
	}

	// 게시글 삭제
	public int communityDelete(int travelPlanNo) {
		return session.update("TravelReviewMapper.communityDelete", travelPlanNo);
	}

	// 게시글 리스트 조회
	public List<CommunityListResponse> communityList() {
		return session.selectList("TravelReviewMapper.communityList");
	}

	// 게시글 수 카운팅
	public int totalCommunity() {
		return session.selectOne("TravelReviewMapper.totalCommunity");
	}

	// 추천수 업데이트

	// 여행지 리뷰 등록
	public int placeReviewWrite(PlaceReviewRegistRequest placeReview) {
		return session.insert("PlaceReviewMapper.placeReviewWrite", placeReview);
	}

	// 여행지 리뷰 수정
	public int placeReviewUpdate(PlaceReviewModifyRequest placeReview) {
		return session.update("PlaceReviewMapper.placeReviewUpdate", placeReview);
	}
}
