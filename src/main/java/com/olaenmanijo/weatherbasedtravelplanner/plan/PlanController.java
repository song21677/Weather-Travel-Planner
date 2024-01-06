package com.olaenmanijo.weatherbasedtravelplanner.plan;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.mapper.CommunityMapper;
import com.olaenmanijo.weatherbasedtravelplanner.domain.member.dto.response.MemberResponse;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dao.PlaceDAO;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain.Place;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PlanController {
    @Autowired
    PlaceDAO placeDAO;

    @Autowired
    PlanDAO planDAO;

    Plan plan = new Plan();

    private final CommunityMapper communityMapper;

    @GetMapping("/plan")
    public String plan(Model model) {
        model.addAttribute("plan", plan);
        model.getAttribute("plan");
        System.out.println(plan);
        return "planPage/plan";
    }

    @PostMapping("/addPlan")
    public String planForm(@RequestBody PlanDTO planDTO, Model model) {

        // 장소 가져오기
        Place place = placeDAO.selectByPlaceNo(Integer.parseInt(planDTO.getPlace_no()));

        // 장소 정보와 장소 방문 날짜, 시간 세팅, 계획 화면에서 보여줄 것
        PlanDTO2 plan2 = new PlanDTO2(planDTO.getDate(), planDTO.getStartHour(),
                planDTO.getEndHour(), place, planDTO.getPlace_color());

        // 진짜 날짜 저장하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // 문자열을 계산하기 위해 LocalDate로 파싱
        LocalDate date = LocalDate.parse(plan.getStartDate(), formatter);

        LocalDate newDate = date.plusDays(Integer.parseUnsignedInt(planDTO.getDate()));

        // 새로운 날짜를 문자열로 변환
        String newDateString = newDate.format(formatter);

        plan2.setRealDate(newDateString);
        plan.add(plan2);

        plan.sortPlaces();

        model.addAttribute("plan", plan);

        return "planPage/planForm";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String area,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String date, Model model)
            throws IOException, URISyntaxException {

        plan.setTitle(title);
        plan.setStartDate(startDate);
        plan.setEndDate(endDate);
 

        if (keyword != null) {
            Map<String, String> paramMap = new HashMap<>();
            if (area == null)
                area = "부산";
            paramMap.put("location", area);
            paramMap.put("name", keyword);

            ArrayList<Place> places = (ArrayList<Place>) placeDAO.selectByNameAndAddress(paramMap);
            model.addAttribute("places", places);
        }

        // db 검색
        if (area != null && category != null) {
            log.error("{}, {}", area, category);
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("category", category);
            paramMap.put("location", area);

            ArrayList<Place> places = (ArrayList<Place>) placeDAO.selectByNameAndCategory(paramMap);

            model.addAttribute("places", places);
        }

        model.addAttribute("plan", plan);
        model.addAttribute("dateString", date);

        return "planPage/searchForm";
    }

    @GetMapping("/savePlan")
    public String save(HttpSession session, @RequestParam String title, Model model) {

        // 일정 저장
        plan.setTitle(title);
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("loginMember");
        plan.setMemberNo(memberResponse.getMemberNo());
        log.error("{}", plan);
        planDAO.insertPlan(plan);

        // 세부 일정 저장
        for (PlanDTO2 planDTO2 : plan.getPlaces()) {
            planDTO2.setPlanNo(plan.getId());
            // planDAO.insertDetailPlan(planDTO2);
            communityMapper.insertDetailPlan(planDTO2);
        }

        plan = new Plan();
        model.addAttribute("plan", plan);
        return "redirect:/plan";
    }
}

