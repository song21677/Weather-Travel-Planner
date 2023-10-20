package com.olaenmanijo.weatherbasedtravelplanner.NaverApi;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

@RestController
public class ReverseGeolocationController {

	
    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/get-address")
    public ResponseEntity<Map<String, String>> getAddress(@RequestParam double lat, @RequestParam double lon,HttpSession session) throws IOException {
    	
    	
    	
    	 Double storedLat = (Double) session.getAttribute("lastLat");
    	 Double storedLon = (Double) session.getAttribute("lastLon");
    	 String storedAddress = (String) session.getAttribute("lastAddress");

    	  if (storedLat != null && storedLon != null && storedAddress != null && storedLat.equals(lat) && storedLon.equals(lon)) {
    	   // 이전에 저장된 위치와 현재 위치가 동일하면 API 호출을 건너뛰고 저장된 주소 정보를 반환합니다.
    	  Map<String, String> result = new HashMap<>();
    	  result.put("address", storedAddress);
    	  return ResponseEntity.ok(result);
    	    }
    	  else {
    	String apiUrl = String.format("https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?request=coordsToaddr&coords=%f,%f&sourcecrs=epsg:4326&orders=admcode,legalcode,addr,roadaddr&output=json",lon,lat);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
        headers.set("X-NCP-APIGW-API-KEY", clientSecret);
        
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        String responseBody = responseEntity.getBody();
        // Parse the JSON response using Jackson's ObjectMapper
        JsonNode jsonResponse = objectMapper.readTree(responseEntity.getBody());
        
        JsonNode addressInfo = jsonResponse.get("results").get(0).get("region");
        String address = addressInfo.get("area1").get("name").asText() + " "
                       + addressInfo.get("area2").get("name").asText() + " "
                       + addressInfo.get("area3").get("name").asText() + " "
                       + addressInfo.get("area4").get("name").asText();

        Map<String, String> result = new HashMap<>();
        result.put("address", address);
        
        session.setAttribute("lastLat", lat);
        session.setAttribute("lastLon", lon);
        session.setAttribute("lastAddress", address);

        

        return ResponseEntity.ok(result);
        }
    }
}