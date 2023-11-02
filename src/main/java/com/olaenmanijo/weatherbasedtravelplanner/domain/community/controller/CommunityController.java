package com.olaenmanijo.weatherbasedtravelplanner.domain.community.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityDetailResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityListResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.response.CommunityPlannerListResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.service.CommunityService;

@Controller
public class CommunityController {

	@Autowired
	CommunityService service;
	/*-
	Community 목록 보기:
	HTTP 메서드: GET
	URL 매핑: /communities
	설명: 커뮤니티 게시물 목록을 조회하는 페이지.
	
	Community 글 쓰기 페이지 (글 작성 양식 표시):
	HTTP 메서드: GET
	URL 매핑: /communities/new
	설명: 새로운 글을 작성하기 위한 페이지. 글 작성 양식을 표시하는 페이지.
	
	Community 글 작성 (글 작성 처리):
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

	// 글쓰기 페이지
	@GetMapping("/communities/newes")
	public String communityWritePage(@RequestParam(value = "plannerReviewId", required = false) Integer travelReviewNo,
			Model model) {
		// 수정일 경우
		if (travelReviewNo != null) {
			CommunityListResponse communityListResponse = service.selectCommunity(travelReviewNo);
			model.addAttribute("CommunityListResponse", communityListResponse);
			int travelPlanNo = communityListResponse.getTRAVEL_PLAN_NO();
			Map<String, List<CommunityDetailResponse>> groupedData = service.selectPlannerDetail(travelPlanNo);

			model.addAttribute("groupedData", groupedData);

		}
		// 신규 등록일 경우
		if (travelReviewNo == null) {
			// member id 값으로 여행일정 리스트 불러오기
			List<CommunityPlannerListResponse> plannerList = service.selectPlannerList(1);
			model.addAttribute("CommunityPlannerListResponse", plannerList);
		}

		return "community/communityWriteBody";
	}

	@GetMapping("/communities/newes/plan-list/{travelPlanNo}")
	public String plannerContext(@PathVariable("travelPlanNo") int travelPlanNo, Model model, HttpSession session) {
		Map<String, List<CommunityDetailResponse>> groupedData = service.selectPlannerDetail(travelPlanNo);

		CommunityListResponse communityListResponse = service.selCommuTravelNo(travelPlanNo);
		model.addAttribute("groupedData", groupedData);
		model.addAttribute("CommunityListResponse", communityListResponse);
		return "community/communityWriteContent";
	}
	
	@GetMapping("/communities/listes")
	public String communityListes(Model model) {
		List<CommunityListResponse> communityListResponse= service.communityList();
		model.addAttribute("CommunityListResponse", communityListResponse);
		return "community/communityList";
	}
	
	@PostMapping("/communities/newes")
	@ResponseBody
	public void communityWrite() {
	}

}
