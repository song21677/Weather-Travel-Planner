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
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.service.CommunityService;
import com.olaenmanijo.weatherbasedtravelplanner.domain.member.dto.response.MemberResponse;
import com.olaenmanijo.weatherbasedtravelplanner.global.file.controller.CloudFileUpload;
import com.olaenmanijo.weatherbasedtravelplanner.global.file.controller.FileUtils;
import com.olaenmanijo.weatherbasedtravelplanner.global.file.dto.request.TravelReviewFileRequest;
import com.olaenmanijo.weatherbasedtravelplanner.global.file.service.FileService;

@Controller
public class CommunityController {

	@Autowired
	CommunityService service;
	FileService fileService;
	FileUtils fileUtils;
	CloudFileUpload cloudFileUpload;

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
		System.out.println("travelPlanNo : " + travelPlanNo);
		Long travelReviewNo = service.travelReviewFindByTravelNo(travelPlanNo).getTravelReviewNo();
		System.out.println("travelReviewNo : " + travelReviewNo);
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
		
		// 파일업로드 추가 
		Long travelReviewNo = params.getTravelReviewNo();
		System.out.println("params:"+  params);
		/*
		List<TravelReviewFileRequest> files = fileUtils.uploadTravelReviewFiles(params.getFiles());
		fileService.saveTravelReviewFiles(travelReviewNo, files);
		params.setPlannerReviewImage(cloudFileUpload(files.get(0).getOriginalName(),files.get(0)));
		System.out.println(params.getPlannerReviewImage());*/
		return service.travelReviewSave(params);
	}

	
	private String cloudFileUpload(String originalName, TravelReviewFileRequest travelReviewFileRequest) {
		// TODO Auto-generated method stub
		return null;
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
	
	
	// 여행기 자세히보기
	@GetMapping("/communities/{travelReviewNo}")
	public String communityRetrieve(@PathVariable final long travelReviewNo, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberResponse memberResponse = (MemberResponse) session.getAttribute("loginMember");
		Long memberNo = memberResponse.getMemberNo();
		
		CommunityResponse communityResponse = service.travelReviewfindById(travelReviewNo);
		Map<String, List<CommunityDetailResponse>> groupedData = service.detailFindByTravelNo(communityResponse.getTravelPlanNo());
		model.addAttribute("groupedData", groupedData);
		System.out.println(groupedData);
		model.addAttribute("CommunityResponse", communityResponse);
		System.out.println(communityResponse);
		model.addAttribute("MemeberNo",memberNo);
		System.out.println(memberNo);
		return "community/communityRetrieve";
	}

}
