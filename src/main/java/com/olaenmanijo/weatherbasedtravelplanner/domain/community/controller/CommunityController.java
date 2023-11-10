package com.olaenmanijo.weatherbasedtravelplanner.domain.community.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.CommunityRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request.PlaceReviewRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityDetailResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityPlanListResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.PlaceReviewResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.service.CommunityService;
import com.olaenmanijo.weatherbasedtravelplanner.domain.member.dto.response.MemberResponse;

@Controller
public class CommunityController {

	@Autowired
	CommunityService service;

	// Community 글 쓰기 페이지
	@GetMapping("/communities/new")
	public String communityWritePage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberResponse memberResponse = (MemberResponse) session.getAttribute("loginMember");
		Long memberNo = memberResponse.getMemberNo();
		List<CommunityPlanListResponse> communityPlanListResponse= service.travelPlanFindByMemberNo(memberNo);
		model.addAttribute("CommunityPlanListResponse", communityPlanListResponse);
		return "community/communityWriteBody";
	}
	
	// Community 글 수정 페이지 (글 수정 양식 표시)
	@GetMapping("/communities/{travelReviewNo}/edit")
	public String communityEditPage(@PathVariable final long travelReviewNo, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberResponse memberResponse = (MemberResponse) session.getAttribute("loginMember");
		Long memberNo = memberResponse.getMemberNo();
		
		List<CommunityPlanListResponse> communityPlanListResponse= service.travelPlanFindByMemberNo(memberNo);
		model.addAttribute("CommunityPlanListResponse", communityPlanListResponse);
		
		Long travelPlanNo = service.travelReviewfindById(travelReviewNo).getTravelPlanNo();
		Map<String, List<CommunityDetailResponse>> groupedData = service.detailFindByTravelNo(travelPlanNo);
		CommunityResponse communityResponse = service.travelReviewFindByTravelNo(travelPlanNo);
		model.addAttribute("groupedData", groupedData);
		model.addAttribute("CommunityResponse", communityResponse);
		
		return "community/communityWriteBody";
	}
	
	// travelPlanNo -> travelReviewNo
	@GetMapping("/travel-Review")
	@ResponseBody
	public Long convertPlanNoToTravelReviewNo(@RequestParam final Long travelPlanNo) {
		Long travelReviewNo = service.travelReviewFindByTravelNo(travelPlanNo).getTravelReviewNo();
		return travelReviewNo;
	}
	
	// Plan 목록 보기
	@GetMapping("/communities/newes/plan-list/{travelPlanNo}")
	public String plannerContext(@PathVariable("travelPlanNo") Long travelPlanNo, Model model, HttpSession session) {
		Map<String, List<CommunityDetailResponse>> groupedData = service.detailFindByTravelNo(travelPlanNo);
		CommunityResponse communityResponse = service.travelReviewFindByTravelNo(travelPlanNo);
		model.addAttribute("groupedData", groupedData);
		model.addAttribute("CommunityResponse", communityResponse);
		return "community/communityWriteContent";
	}
	
	// Community 목록 보기
	@GetMapping("/communities")
	public String communityListes(Model model) {
		List<CommunityResponse> communityResponse= service.travelReviewFindAll();
		model.addAttribute("CommunityResponse", communityResponse);
		return "community/communityList";
	}
	
	// Community 글 작성 (글 작성 처리)
	@PostMapping("/communities")
	@ResponseBody
	public Long communityWrite(HttpServletRequest request, @RequestBody final CommunityRequest params) {
		HttpSession session = request.getSession();
		MemberResponse memberResponse = (MemberResponse) session.getAttribute("loginMember");
		params.setMemberNo(memberResponse.getMemberNo());
		return service.travelReviewSave(params);
	}

	
	// Community 글 수정 (글 수정 처리)
	@PutMapping("/communities")
	@ResponseBody
	public Long communityModify(HttpServletRequest request, @RequestBody final CommunityRequest params) {
		HttpSession session = request.getSession();
		MemberResponse memberResponse = (MemberResponse) session.getAttribute("loginMember");
		params.setMemberNo(memberResponse.getMemberNo());
		return service.travelReviewUpdate(params);
	}
	
	// 여행지 리뷰 수 카운팅(여행지 등록 및 삭제 여부)
	@GetMapping("/placeReview-count")
	@ResponseBody
	public Long countBydetailPlanNo(@RequestParam final Long detailPlanNo) {
		return service.countBydetailPlanNo(detailPlanNo);
	}
	
	// 여행지 리뷰 작성
	@PostMapping("/placeReview")
	@ResponseBody
	public Long placeReviewWrite(HttpServletRequest request, @RequestBody final PlaceReviewRequest params) {
		HttpSession session = request.getSession();
		MemberResponse memberResponse = (MemberResponse) session.getAttribute("loginMember");
		params.setMemberNo(memberResponse.getMemberNo());
		return service.placeReviewWrite(params);
	}
	
	// 여행지 리뷰 수정
	@PutMapping("/placeReview")
	@ResponseBody
	public Long placeReviewModify(HttpServletRequest request, @RequestBody final PlaceReviewRequest params) {
		HttpSession session = request.getSession();
		MemberResponse memberResponse = (MemberResponse) session.getAttribute("loginMember");
		params.setMemberNo(memberResponse.getMemberNo());
		Long detailPlanNo = params.getDetailPlanNo();
		Long placeReviewNo = service.placeReviewFindByDetailNo(detailPlanNo).getPlaceReviewNo();
		params.setPlaceReviewNo(placeReviewNo);
		System.out.println(params);
		return service.placeReviewWrite(params);
	}

	
	/*-
	-Community 목록 보기:
	HTTP 메서드: GET
	URL 매핑: /communities
	설명: 커뮤니티 게시물 목록을 조회하는 페이지.
	
	-Community 글 쓰기 페이지 (글 작성 양식 표시):
	HTTP 메서드: GET
	URL 매핑: /communities/new
	설명: 새로운 글을 작성하기 위한 페이지. 글 작성 양식을 표시하는 페이지.
	
	-Community 글 작성 (글 작성 처리):
	HTTP 메서드: POST
	URL 매핑: /communities
	설명: 새로운 글을 작성하고 서버에 저장하는 엔드포인트. 사용자가 글 작성 양식을 제출할 때 호출됩니다.
	
	Community 글 자세히 보기:
	HTTP 메서드: GET
	URL 매핑: /communities/{글ID}
	설명: 특정 글의 자세한 내용을 보여주는 페이지. {글ID}는 글의 고유 식별자입니다.
	
	Community 글 수정 페이지 (글 수정 양식 표시):
	HTTP 메서드: GET
	URL 매핑: /communities/{글ID}/edit
	설명: 특정 글을 수정하기 위한 페이지. 글 수정 양식을 표시하는 페이지.
	
	Community 글 수정 (글 수정 처리):
	HTTP 메서드: PUT 또는 PATCH
	URL 매핑: /communities/{글ID}
	설명: 특정 글을 수정하고 서버에 업데이트하는 엔드포인트. 사용자가 글 수정 양식을 제출할 때 호출됩니다.
	
	Community 글 삭제:
	HTTP 메서드: DELETE
	URL 매핑: /communities/{글ID}
	설명: 특정 글을 삭제하는 엔드포인트. 글의 고유 식별자를 사용하여 글을 삭제합니다.
	*/
}
