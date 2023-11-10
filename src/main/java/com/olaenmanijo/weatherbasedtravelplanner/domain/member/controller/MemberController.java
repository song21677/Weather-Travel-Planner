package com.olaenmanijo.weatherbasedtravelplanner.domain.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.olaenmanijo.weatherbasedtravelplanner.domain.member.dto.request.MemberRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.member.dto.response.MemberResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	// 로그인 페이지
	@GetMapping("/login.do")
	public String openLogin() {
		return "member/login";
	}

	// 회원 정보 저장 (회원가입)
	@PostMapping("/members")
	@ResponseBody
	public Long saveMember(@RequestBody final MemberRequest params) {
		return memberService.saveMember(params);
	}

	// 회원 상세정보 조회
	@GetMapping("/members/{memberId}")
	@ResponseBody
	public MemberResponse findMemberByLoginId(@PathVariable final String memberId) {
		return memberService.findMemberByLoginId(memberId);
	}

	// 회원 정보 수정
	@PatchMapping("/members/{memberNo}")
	@ResponseBody
	public Long updateMember(@PathVariable final long memberNo, @RequestBody final MemberRequest params) {
		return memberService.updateMember(params);
	}

	// 회원 정보 삭제 (회원 탈퇴)
	@DeleteMapping("/members/{memberNo}")
	@ResponseBody
	public Long deleteMemberById(final Long memberNo) {
		return memberService.deleteMemberById(memberNo);
	}

	// 회원 수 카운팅 (ID 중복 체크)
	@GetMapping("/member-count")
	@ResponseBody
	public Long countMemberByLoginId(@RequestParam final String memberId) {
		return memberService.countMemberByLoginId(memberId);
	}

	// 로그인
	@PostMapping("/login")
	@ResponseBody
	public MemberResponse login(HttpServletRequest request, @RequestParam Map<String, String> params) {
		// 1. 회원 정보 조회
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		MemberResponse member = memberService.login(loginId, password);

		// 2. 세션에 회원 정보 저장 & 세션 유지 시간 설정 (30분)
		if (member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", member);
			session.setMaxInactiveInterval(60 * 30);
		}

		return member;
	}

	// 로그아웃
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}
}
