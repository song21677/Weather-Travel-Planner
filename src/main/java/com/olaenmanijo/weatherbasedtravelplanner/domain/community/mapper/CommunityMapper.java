package com.olaenmanijo.weatherbasedtravelplanner.domain.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.CommunityRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.PlaceReviewRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityDetailResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityPlanListResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.PlaceReviewResponse;

@Mapper
public interface CommunityMapper {
	
	/**
     * 여행기 저장 - 여행기 작성페이지-
     * @param params - 게시글 정보
     */
    void travelReviewSave(CommunityRequest params);

    /**
     * 게시글 상세정보 조회 - 여행기 상세보기페이지, 수정페이지-
     * @param travelReviewNo - PK
     * @return 게시글 상세정보
     */
    CommunityResponse travelReviewFindById(Long travelReviewNo);
    
    /**
     * 여행블럭정보 조회 - 여행기 상세보기페이지, 작성페이지-
     * @param travelPlanNo
     * @return 여행일정 상세정보
     */
    List<CommunityDetailResponse> travelDetailPlanFindByTravelNo(Long travelPlanNo);
    
    /**
     * 게시글 여행계획번호로 조회 - 여행기 작성페이지(여행계획 하나당 여행기 하나만 쓸 수 있음)-
     * 여행일정 불러오기 -> 여행계획 리스트 띄우기 -> 여행계획선택 -> 여행계획번호로 조회 
     * -> 있으면 여행기 정보 불러와서 수정
     * -> 없으면 여행계획 정보 불러와서 등록
     * -> Get 요청으로 여행기 번호 입력 -> 수정페이지 (travelReviewNo, {{여행기정보},{여행블럭정보}})
     * @param travelPlanNo
     * @return 게시글 정보(작성내용)
     */
    CommunityResponse travelReviewFindByTravelNo(Long travelPlanNo);
    
    /**
     * 여행일정 리스트 조회 - 여행기 작성페이지-
     * @param memberNo
     * @return 여행일정 리스트
     */
    List<CommunityPlanListResponse> travelPlanFindByMemberNo(Long memberNo);
    
    CommunityPlanListResponse travelPlanFindByNo(Long travelPlanNo);
    
    /**
     * 여행지 리뷰 여행블럭번호로 조회 - 여행기 작성페이지-
     * @param detailPlanNo
     * @return 여행지 리뷰 정보
     */
    PlaceReviewResponse placeReviewFindByDetailNo(Long detailPlanNo);
    
    /**
     * 여행지 리뷰 멤버번호로 조회 - 마이페이지-
     * @param memberNo
     * @return 여행블럭정보 리스트
     */
    List<PlaceReviewResponse> placeReviewFindByMemberNo(Long memberNo);
    
    /**
     * 여행지 리뷰 여행지번호로 조회 - 여행정보-
     * @param placeNo
     * @return 여행블럭정보 리스트
     */
    List<PlaceReviewResponse> placeReviewFindByPlaceNo(Long placeNo);
    
    /**
     * 여행지 리뷰 저장 - 여행기 작성페이지-
     * @param params - 여행지 리뷰 정보
     */
    void placeReviewWrite(PlaceReviewRequest params);
    
    /**
     * 여행지 리뷰 수정 - 여행기 작성페이지-
     * @param params - 여행지 리뷰 정보
     */
    void placeReviewUpdate(PlaceReviewRequest params);
    
    /**
     * 여행기 수정 - 여행기 작성페이지-
     * @param params - 게시글 정보
     */
    void travelReviewUpdate(CommunityRequest params);

    /**
     * 게시글 삭제 - 마이페이지
     * @param travelReviewNo - PK
     */
    void travelReviewDeleteById(Long travelReviewNo);

    /**
     * 게시글 리스트 조회 - 여행기 메인페이지-
     * @return 게시글 리스트
     */
    List<CommunityResponse> travelReviewFindAll();

    /**
     * 게시글 수 카운팅
     * @return 게시글 수
     */
    int travelReviewCount();
    
    /**
     * 게시글 조회수 업데이트 - 여행기 상세보기페이지 시 업데이트
     * @param travelReviewNo - PK
     */
    void travelReviewReadHit(Long travelReviewNo);
    
    /**
     * 여행지 리뷰 수 카운팅 (등록 수정 여부 확인)-
     * @param detailPlanNo - UK
     * @return 여행지 리뷰 수
     */
    long countBydetailPlanNo(Long detailPlanNo);
     
}
