package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import java.net.URI;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SpecialWeatherServiceImpl implements SpecialWeatherService{
	
	@Autowired
	SpecialWeatherDAO dao;
	
	@Value("${specialWeather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String serviceKey;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();



	@Override
	public void setWeatherData(String fromTmFc, String toTmFc, String type, String numOfRows) throws Exception {
		String requestUrl = buildRequestUrl(fromTmFc,toTmFc,  type, numOfRows);
        URI uri = new URI(requestUrl);
        System.out.println(uri);
        String response = restTemplate.getForObject(uri, String.class);
        
        if (response.contains("<OpenAPI_ServiceResponse>")) {
            // API 응답이 XML인 경우
            return;
        }
        // JSON 파싱 및 데이터 추출
        JsonNode itemNode = objectMapper.readTree(response).path("response").path("body").path("items").path("item");
        for (JsonNode item : itemNode) {
        	SpecialWeatherDTO dto = new SpecialWeatherDTO();
	         dto.setTM_FC(item.path("tmFc").asText());
			 dto.setAREA_NAME(item.path("areaName").asText());
			 dto.setWARN_VAR(item.path("warnVar").asText());
			 dto.setWARN_STRESS(item.path("warnStress").asInt());
			 dto.setCOMMAND(item.path("command").asInt());
			 dto.setTIME(item.path("startTime").asText());
			 dto.setCANCEL(item.path("cancel").asInt());
			 dao.setWeatherData(dto);
		}
        
        
	}
	
	
	//쿼리 작성기
    private String buildRequestUrl(String fromTmFc, String toTmFc, String type, String numOfRows) throws Exception {
        return new StringBuilder(apiUrl)
                .append("?serviceKey=").append(URLEncoder.encode(serviceKey,"UTF-8"))
                .append("&numOfRows=").append(URLEncoder.encode(numOfRows, "UTF-8"))
                .append("&pageNo=").append(URLEncoder.encode("1", "UTF-8"))
                .append("&dataType=").append(URLEncoder.encode(type, "UTF-8"))
                .append("&fromTmFc=").append(URLEncoder.encode(fromTmFc, "UTF-8"))
                .append("&toTmFc=").append(URLEncoder.encode(toTmFc, "UTF-8"))
                .append("&stnId=").append(URLEncoder.encode("108", "UTF-8"))
                .toString();
    }
    
    public int keepdateSpecialWeather() {
		return dao.keepdateSpecialWeather();
	}

}
