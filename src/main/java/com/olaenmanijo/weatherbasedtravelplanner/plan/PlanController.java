package com.olaenmanijo.weatherbasedtravelplanner.plan;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.olaenmanijo.weatherbasedtravelplanner.domain.community.mapper.CommunityMapper;
import com.olaenmanijo.weatherbasedtravelplanner.domain.member.dto.response.MemberResponse;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dao.PlaceDAO;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.domain.Place;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto.Item;

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
   
   // @Autowired
   Plan plan = new Plan();
   
   private final CommunityMapper communityMapper;

   @GetMapping("/plan")
   public String plan(@ModelAttribute Plan plan) {
      return "planPage/plan";
   }
   
   //@ResponseBody
   @PostMapping("/addPlan")
   public String planForm(@RequestBody PlanDTO planDTO, Model model) {
      
      //log.error("start: {}, end: {}", plan.getStartDate(), plan.getEndDate());
      // 장소 가져오기
      Place place = placeDAO.selectByPlaceNo(Integer.parseInt(planDTO.getPlace_no()));
      
      // 장소 정보와 장소 방문 날짜, 시간 세팅, 계획 화면에서 보여줄 것
      PlanDTO2 plan2 = new PlanDTO2(planDTO.getDate(), planDTO.getStartHour(), planDTO.getEndHour(), place);
      
      //log.error("{}, {}, {}", String.valueOf(place.getPlace_no()), place.getPlace_name(), place.getPlace_category());
      
      // 일정 저장
      // planDAO.insertPlan(plan);
      
      // 일정 아이디 세부 일정에 저장 (1)
      //plan2.setPlanNo(plan.getId());
      
      // 진짜 날짜 저장하기 (2)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // 문자열을 계산하기 위해 LocalDate로 파싱
        LocalDate date = LocalDate.parse(plan.getStartDate(), formatter);

        // 3일을 더하기
        LocalDate newDate = date.plusDays(Integer.parseUnsignedInt(planDTO.getDate()));

        // 새로운 날짜를 문자열로 변환
        String newDateString = newDate.format(formatter);

        //log.error("Original Date: " + planDTO.getDate());
        //log.error("New Date: " + newDateString);
        
      plan2.setRealDate(newDateString);
      //log.error("{}", plan2);
      
      // 세부 일정 저장
//      for (PlanDTO2 planDTO2 : plan.getPlaces()) {
//         planDAO.insertDetailPlan(planDTO2);
//      }
      plan.add(plan2);
      
      //for (PlanDTO2 aplace : plan.getPlaces()) {
          //  System.out.println(aplace);
        //}
      
      plan.sortPlaces();
      
      for (PlanDTO2 aplace : plan.getPlaces()) {
            System.out.println(aplace);
        }
      
      System.out.println();
      model.addAttribute("plan", plan);
      
      //placeDAO.selectByNo();
      
      //plan.add(new Item(planDTO.getPlace()));
      //log.error("{}", planDTO); 
      //log.error("{}", plan);
      //log.error("{}, {}, {}", plan.getDaterange(), plan.getTitle(), plan.getPlaces().get(0));
      
      return "planPage/planForm";
   }
   
//   @GetMapping("/test")
//   public void test() throws URISyntaxException {
//      RestTemplate restTemplate = new RestTemplate();
//      URI uri = new URI("http://localhost:8082/planForm");
//      ResponseEntity<Plan> response = restTemplate.postForEntity(uri, new PlanDTO("DD", "DDD", new Place(1, "음식점", "또오리", "충경로135")), Plan.class);
//        Plan plan = response.getBody();
//        log.error("{}", plan);
//   }
   
   @GetMapping("/search")
   public String search(@RequestParam(required = false) String title, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate, @RequestParam(required = false) String date,  @RequestParam(required = false) String area, @RequestParam(required = false) String category, @RequestParam(required = false) String keyword, Model model) throws IOException, URISyntaxException {
      
      plan.setTitle(title);
      plan.setStartDate(startDate);
      plan.setEndDate(endDate);
      
      log.error("{}", keyword);
      log.error("{}, {}", area, category);
      //log.error("{}, start: {}, end: {}, date: {}", plan.getTitle(), plan.getStartDate(), plan.getEndDate(), date);
      
      // db 검색
      if (area != null && category != null) {
    	  log.error("{}, {}", area, category);
         Map<String, String> paramMap = new HashMap<>();
           paramMap.put("category", category);
           paramMap.put("location", area);
         
           ArrayList<Place> places = (ArrayList<Place>) placeDAO.selectByNameAndCategory(paramMap);
           for (Place place : places) {
              log.error("{}", place);
           }
           
//           ArrayList<Place> places = new ArrayList<>();
//           log.error("{}", area.equals("가평") && category.equals("음식점"));
//           if (area.equals("가평") && category.equals("음식점")) {
//              places.add(new Place(220474, "음식점", "원흥식당", "경기도 가평군 조종면 현등사길 7-42"));
//              places.add(new Place(220308, "음식점", "원조닭갈비막국수", "경기도 가평군 상면 수목원로 4-5"));
//              places.add(new Place(220341, "음식점", "원조장작불곰탕", "경기도 가평군 경춘로 980"));
//              places.add(new Place(212350, "음식점", "시골밥상닭갈비", "경기도 가평군 가평읍 경춘로 1793"));
//              places.add(new Place(210957, "음식점", "송원막국수", "경기도 가평군 가화로 76-1 송원막국수"));
//           }
//           
           
//           if (area.equals("강릉") && keyword.equals("초당 순두부")) {
//              places.add(new Place(220349, "음식점", "원조초당순두부", "강원특별자치도 강릉시 초당순두부길77번길 9"));
//              places.add(new Place(217765, "음식점", "오월의초당", "강원특별자치도 강릉시 난설헌로 234-5"));
//              places.add(new Place(227649, "음식점", "초당골순두부", "강원특별자치도 강릉시 초당순두부길 33-1"));
//              places.add(new Place(227653, "음식점", "초당맷돌순두부", "강원특별자치도 강릉시 초당순두부길 75"));
//              places.add(new Place(227655, "음식점", "초당본점", "강원특별자치도 속초시 관광로 440"));
//           }
//           //ArrayList<Place> places = new ArrayList<>();
//           //places.add(new Place())
           
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
	  log.error("{}", memberResponse.getMemberNo());
      planDAO.insertPlan(plan);   
      
      // 세부 일정 저장
      for (PlanDTO2 planDTO2 : plan.getPlaces()) {
         planDTO2.setPlanNo(plan.getId());
         //planDAO.insertDetailPlan(planDTO2);
         communityMapper.insertDetailPlan(planDTO2);
      }      
      
      plan = new Plan();
      model.addAttribute("plan", plan);
      log.error("{}", plan);
      return "redirect:/plan";
   }
}
 
