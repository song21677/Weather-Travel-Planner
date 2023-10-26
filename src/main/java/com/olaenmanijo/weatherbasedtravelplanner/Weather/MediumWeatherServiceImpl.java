package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import java.net.URI;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MediumWeatherServiceImpl implements MediumWeatherService {
	
	@Autowired
	MediumWeatherDAO dao;
	
	 @Value("${mediumweather1.api.url}")
	    private String apiUrl1;
	 
	 @Value("${mediumweather2.api.url}")
	    private String apiUrl2;

	 @Value("${weather.api.key}")
	    private String serviceKey;

	    private final ObjectMapper objectMapper = new ObjectMapper();
	    private final RestTemplate restTemplate = new RestTemplate();
	    
	    
	@Override
	public void getWeatherData1(String tmFc, String type, String numOfRows, String regid, int no) throws Exception {
			String requestUrl = buildRequestUrl1(tmFc, type, numOfRows,regid);
	        URI uri = new URI(requestUrl);
	        System.out.println(uri);
	        String response = restTemplate.getForObject(uri, String.class);
	        
	        
	        // JSON 파싱 및 데이터 추출
	        JsonNode rootNode = objectMapper.readTree(response);
	        JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");
	        
	        LocalDate currentDate = LocalDate.parse(tmFc.substring(0, 8),DateTimeFormatter.ofPattern("yyyyMMdd"));

	        for (JsonNode item : itemsNode) {
	        	MediumTerForecastDTO mediumforecastDTO = new MediumTerForecastDTO();

	            for (int i = 3; i <= 10; i++) {
	                LocalDate forecastDate = currentDate.plusDays(i);
	                String taMinField = "taMin" + i;
	                String taMaxField = "taMax" + i;
	                
	                mediumforecastDTO.setSECOND_PRECINCT_NO(no);
	                mediumforecastDTO.setANNOUNCE_DAY(currentDate.toString());
	                mediumforecastDTO.setFORECAST_DAY(forecastDate.toString());
	                mediumforecastDTO.setTAMIN(item.path(taMinField).asInt());
	                mediumforecastDTO.setTAMAX(item.path(taMaxField).asInt());
	                
	                // 이 시점에서 DTO를 데이터베이스에 저장하거나 리스트에 추가하여 나중에 저장할 수도 있습니다.
	                submit(mediumforecastDTO);
	            }
	        }
	    }
	    
	    

	@Override
	public void getWeatherData2(String tmFc, String type, String numOfRows, String regid, int no) throws Exception {
			String requestUrl = buildRequestUrl2(tmFc, type, numOfRows,regid);
	        URI uri = new URI(requestUrl);
	        System.out.println(uri);
	        String response = restTemplate.getForObject(uri, String.class);
	        // JSON 파싱 및 데이터 추출
	        JsonNode rootNode = objectMapper.readTree(response);
	        JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");
	        String date = tmFc.substring(0, 8);
	        for (JsonNode item : itemsNode) {
	            String currentFcstTime = item.path("fcstTime").asText();

	        
		
	}}
	
	
	//쿼리 작성기
    private String buildRequestUrl1(String tmFc, String type, String numOfRows, String regid) throws Exception {
        return new StringBuilder(apiUrl1)
                .append("?serviceKey=").append(URLEncoder.encode(serviceKey,"UTF-8"))
                .append("&numOfRows=").append(URLEncoder.encode(numOfRows, "UTF-8"))
                .append("&pageNo=").append(URLEncoder.encode("1", "UTF-8"))
                .append("&dataType=").append(URLEncoder.encode(type, "UTF-8"))
                .append("&regId=").append(URLEncoder.encode(regid, "UTF-8"))
                .append("&tmFc=").append(URLEncoder.encode(tmFc, "UTF-8"))
                .toString();
    }
	
	
	//쿼리 작성기
    private String buildRequestUrl2(String tmFc, String type, String numOfRows, String regid) throws Exception {
        return new StringBuilder(apiUrl2)
                .append("?serviceKey=").append(URLEncoder.encode(serviceKey,"UTF-8"))
                .append("&numOfRows=").append(URLEncoder.encode(numOfRows, "UTF-8"))
                .append("&pageNo=").append(URLEncoder.encode("1", "UTF-8"))
                .append("&dataType=").append(URLEncoder.encode(type, "UTF-8"))
                .append("&regId=").append(URLEncoder.encode(regid, "UTF-8"))
                .append("&tmFc=").append(URLEncoder.encode(tmFc, "UTF-8"))
                .toString();
    }
    
    @Override
    @Transactional
    public void submit(MediumTerForecastDTO mediumforecastDTO) {
    	dao.insertTemp(mediumforecastDTO);
    }




}
