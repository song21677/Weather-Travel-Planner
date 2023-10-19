package com.olaenmanijo.weatherbasedtravelplanner.MainPage;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
	
	@GetMapping("/main")
	public String main(Model model, HttpSession session) {
	    // 예시: 세션에서 'user' 속성을 확인하여 로그인 상태 결정
	    Object user = session.getAttribute("user");
	    model.addAttribute("isLoggedIn", user != null);
	    return "/mainPage/main";
	}

}
