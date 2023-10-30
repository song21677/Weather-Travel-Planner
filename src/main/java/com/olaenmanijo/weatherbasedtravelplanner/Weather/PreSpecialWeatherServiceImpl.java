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
public class PreSpecialWeatherServiceImpl implements PreSpecialWeatherService {
	
	@Autowired
	PreSpecialWeatherDAO dao;
	
	@Value("${preSpecialWeather.api.url}")
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
        // JSON 파싱 및 데이터 추출
        JsonNode itemNode = objectMapper.readTree(response).path("response").path("body").path("items").path("item");
        for (JsonNode item : itemNode) {
        	PreSpecialWeatherDTO dto = new PreSpecialWeatherDTO();
			 dto.setPWN(item.path("pwn").asText());
			 dto.setREM(item.path("rem").asText());
			 dto.setTM_FC(item.path("tmFc").asText());
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
                .append("&stnId=").append(URLEncoder.encode("108", "UTF-8"))
                .append("&fromTmFc=").append(URLEncoder.encode(fromTmFc, "UTF-8"))
                .append("&toTmFc=").append(URLEncoder.encode(toTmFc, "UTF-8"))
                .toString();
    }
    
    public int keepdateSpecialWeather() {
		return dao.keepdatePreSpecialWeather();
	}
    
 

}
