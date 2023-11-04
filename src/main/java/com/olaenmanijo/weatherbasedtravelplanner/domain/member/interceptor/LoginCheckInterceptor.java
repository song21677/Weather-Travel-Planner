package com.olaenmanijo.weatherbasedtravelplanner.domain.member.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.olaenmanijo.weatherbasedtravelplanner.domain.member.dto.response.MemberResponse;

public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 1. 세션에서 회원 정보 조회
		HttpSession session = request.getSession();
		MemberResponse member = (MemberResponse) session.getAttribute("loginMember");

		// 2. 회원 정보 체크
		if (member == null || member.getDeleteYN() == true) {
			response.sendRedirect("/login.do");
			return false;
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
