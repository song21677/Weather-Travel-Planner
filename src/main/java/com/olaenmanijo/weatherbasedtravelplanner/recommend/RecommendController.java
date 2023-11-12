package com.olaenmanijo.weatherbasedtravelplanner.recommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RecommendController {


    @Autowired
    RecommendService service;
    
    @Autowired
    AgeService ageservice;

    @GetMapping("/recommend")
    public String recommend(Model model) {
        return "recommend/recommendList";
    }

    @ResponseBody
    @PostMapping("/get-additional-data")
    public ResponseEntity<Object> getAdditionalData(@RequestParam Double lat, @RequestParam Double lon,
            @RequestParam String address) {
        	String locate=null;
    	
            ResponseEntity<Object> additionalData = service.getAdditionalData(lat, lon, address,locate);

            if (additionalData != null) {
                return ResponseEntity.ok(additionalData);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("추가 데이터를 검색할 수 없습니다.");
            }

    }
    
    @ResponseBody
    @PostMapping("/get-additional-data2")
    public ResponseEntity<Object> getAdditionalData2(@RequestParam String locate) {
        
            ResponseEntity<Object> additionalData = service.getAdditionalData2(locate);
            
            if (additionalData != null) {
                return ResponseEntity.ok(additionalData);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("추가 데이터를 검색할 수 없습니다.");
            }

    }
    
    
    @ResponseBody
    @PostMapping("/get-age-data")
    public ResponseEntity<Object> getAgeData(@RequestParam Double lat, @RequestParam Double lon) {
    	
            ResponseEntity<Object> additionalData = ageservice.getAgeData(lat, lon);

            if (additionalData != null) {
                return ResponseEntity.ok(additionalData);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("추가 데이터를 검색할 수 없습니다.");
            }

    }
    
    @ResponseBody
    @PostMapping("/get-age-data2")
    public ResponseEntity<Object> getAgeData(@RequestParam String age) {
    	
            ResponseEntity<Object> additionalData = ageservice.getAgeData2(age);

            if (additionalData != null) {
                return ResponseEntity.ok(additionalData);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("추가 데이터를 검색할 수 없습니다.");
            }

    }
    

}
