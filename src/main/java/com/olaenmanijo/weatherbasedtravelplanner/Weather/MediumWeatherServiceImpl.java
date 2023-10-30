package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import java.net.URI;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
	public void getWeatherData1(String tmFc, String type, String numOfRows, String regid, int no , int no2) throws Exception {
			String requestUrl = buildRequestUrl1(tmFc, type, numOfRows,regid);
	        URI uri = new URI(requestUrl);
	        System.out.println(uri);
	        String response = restTemplate.getForObject(uri, String.class);
	        
	        
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	        
	        JsonNode itemNode = objectMapper.readTree(response).path("response").path("body").path("items").path("item").get(0);
	        LocalDate currentDate = LocalDate.parse(tmFc.substring(0, 8),formatter);
	        String currentHour = tmFc.substring(8, 10); // 시간 부분 추출


	            for (int i = 3; i <= 10; i++) {
	            	MediumTerForecastDTO mediumforecastDTO = new MediumTerForecastDTO();
	                LocalDate forecastDate = currentDate.plusDays(i);
	                String taMinField = "taMin" + i;
	                String taMaxField = "taMax" + i;
	                
	                mediumforecastDTO.setSECOND_PRECINCT_NO(no);
	                mediumforecastDTO.setFIRST_PRECINCT_NO(no2);
	                mediumforecastDTO.setANNOUNCE_DAY(tmFc);
	                mediumforecastDTO.setFORECAST_DAY(forecastDate.format(formatter));
	             // 18시인 경우 TAMIN과 TAMAX를 null로 설정
	                if ("18".equals(currentHour)) {
	                    mediumforecastDTO.setTAMIN(0);
	                    mediumforecastDTO.setTAMAX(0);
	                } else {
	                    mediumforecastDTO.setTAMIN(itemNode.path(taMinField).asInt());
	                    mediumforecastDTO.setTAMAX(itemNode.path(taMaxField).asInt());
	                }
	                
	                // 이 시점에서 DTO를 데이터베이스에 저장하거나 리스트에 추가하여 나중에 저장할 수도 있습니다.
	                submit(mediumforecastDTO);
	            }
	        
	    }
	    
	    

	@Override
	public void getWeatherData2(String tmFc, String type, String numOfRows, String regid, int no) throws Exception {
			String requestUrl = buildRequestUrl2(tmFc, type, numOfRows,regid);
	        URI uri = new URI(requestUrl);
	        System.out.println(uri);
	        String response = restTemplate.getForObject(uri, String.class);
	        // JSON 파싱 및 데이터 추출
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	        
	        JsonNode itemNode = objectMapper.readTree(response).path("response").path("body").path("items").path("item").get(0);
	        LocalDate currentDate = LocalDate.parse(tmFc.substring(0, 8),formatter);
	        

	        for (int day = 3; day <= 10; day++) {
	        	
	        	String wfAmField, wfPmField, rnStAmField, rnStPmField;
	            String wfAm = null;
	            String wfPm = null;
	            int rnStAm = 0;
	            int rnStPm = 0;
	            MediumTerForecastDTO mediumforecastDTO2= new MediumTerForecastDTO();
	            LocalDate forecastDate = currentDate.plusDays(day);
	            
	            if(day < 8) {
	            wfAmField = "wf" + day + "Am";
	            wfPmField = "wf" + day + "Pm";
	            rnStAmField = "rnSt" + day + "Am";
	            rnStPmField = "rnSt" + day + "Pm";

	            wfAm = itemNode.path(wfAmField).asText();
	            wfPm = itemNode.path(wfPmField).asText();
	            rnStAm = itemNode.path(rnStAmField).asInt();
	            rnStPm = itemNode.path(rnStPmField).asInt();
	            }
	            // 8, 9, 10일 후의 경우 AM, PM 값을 동일하게 저장
	            if (day >= 8) {
	            wfAmField = "wf" + day;
	 	        wfPmField = "wf" + day;
	 	        rnStAmField = "rnSt" + day;
	 	        rnStPmField = "rnSt" + day;	
	            	
	 	        wfAm = itemNode.path(wfAmField).asText();
	            wfPm = itemNode.path(wfPmField).asText();
	            rnStAm = itemNode.path(rnStAmField).asInt();
	            rnStPm = itemNode.path(rnStPmField).asInt();	
	            
	            }
	            mediumforecastDTO2.setFIRST_PRECINCT_NO(no);
	            mediumforecastDTO2.setANNOUNCE_DAY(tmFc);
	            mediumforecastDTO2.setFORECAST_DAY(forecastDate.format(formatter));

	            mediumforecastDTO2.setWFAM(wfAm);
	            mediumforecastDTO2.setWFPM(wfPm);
	            mediumforecastDTO2.setRNSTAM(rnStAm);
	            mediumforecastDTO2.setRNSTPM(rnStPm);

	        
	            submit(mediumforecastDTO2);
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
    	  if (mediumforecastDTO.getRNSTAM()== 0 && mediumforecastDTO.getRNSTPM()==0 && 
    	      mediumforecastDTO.getWFAM()==null && mediumforecastDTO.getWFPM()==null) {
    	        dao.insertTemp(mediumforecastDTO);
    	    } else {
    	        dao.updateTemp(mediumforecastDTO);
    }
    	  
    }
    
    public void keepdateTemp() {
    	dao.keepdateTemp();
    }	  
    
    public List<String> findMediumAnn() {
    	return dao.findMediumAnn();
    }



}
