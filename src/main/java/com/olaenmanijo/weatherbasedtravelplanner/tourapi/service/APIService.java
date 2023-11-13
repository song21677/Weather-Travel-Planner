package com.olaenmanijo.weatherbasedtravelplanner.tourapi.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto.Item;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto.Items;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto.ResponseDTO;
import com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto.Response;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.impl.client.HttpClientBuilder;

import org.springframework.http.ResponseEntity;

// http://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=xSNjOLnnZKF%2B4zEt6Y7%2Bk79dqA76nW9NSDdStY2MsFcvF3zkLs1gLGOAyg5bVWoJhBT8BQeRPsY%2FRYsPu2Ukfg%3D%3D&numOfRows=10&pageNo=0&
public class APIService {
	private static final Logger logger = LoggerFactory.getLogger(APIService.class);
	
	String baseUrl = "http://apis.data.go.kr";
	String subUrl = "/B551011/KorService1/";
	String serviceKey = "xSNjOLnnZKF%2B4zEt6Y7%2Bk79dqA76nW9NSDdStY2MsFcvF3zkLs1gLGOAyg5bVWoJhBT8BQeRPsY%2FRYsPu2Ukfg%3D%3D";
	
	public List<Item> requestCommonInfo() throws UnsupportedEncodingException {
		
		String sub = "detailCommon1";
		String url = new StringBuilder(baseUrl + subUrl + sub)
				.append("?serviceKey=").append(serviceKey)
                .append("&MobileOS=").append(URLEncoder.encode("ETC", "UTF-8"))
                .append("&MobileApp=").append(URLEncoder.encode("WeatherPlan", "UTF-8"))
                .append("&_type=").append(URLEncoder.encode("json", "UTF-8"))
                .append("&contentId").append(URLEncoder.encode("6", "UTF-8"))
                .toString();
		
		return null;
	}
	
	public List<Item> requestToTourListBasedAreaAPI() throws IOException, URISyntaxException {
		String sub = "areaBasedList1";
		String url = new StringBuilder(baseUrl + subUrl + sub)
                .append("?serviceKey=").append(serviceKey)
                .append("&pageNo=").append(URLEncoder.encode("1", "UTF-8"))
                .append("&numOfRows=").append(URLEncoder.encode("55000", "UTF-8"))
                .append("&MobileOS=").append(URLEncoder.encode("ETC", "UTF-8"))
                .append("&MobileApp=").append(URLEncoder.encode("WeatherPlan", "UTF-8"))
                //.append("&areaCode=").append(URLEncoder.encode("6", "UTF-8"))
                //.append("&contentTypeId=").append(URLEncoder.encode("39", "UTF-8"))
                .append("&_type=").append(URLEncoder.encode("json", "UTF-8"))
                .toString();

		URI uri = new URI(url);
        System.out.println(uri.toString());
        
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(3000);
        
        int timeout = 5;
        RequestConfig config = RequestConfig.custom()
        		.setConnectTimeout(timeout * 1000)
        		.setConnectionRequestTimeout(timeout * 1000)
        		.setSocketTimeout(timeout * 1000)
        		.build();
        
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(100)
                .setMaxConnPerRoute(5)
                .setDefaultRequestConfig(config)
                .build();
        
        factory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(factory);
     
        // 요청 보내기
        ResponseEntity<ResponseDTO> response = restTemplate.getForEntity(uri, ResponseDTO.class);

        if (response.getStatusCodeValue() == 200) {
            ResponseDTO responseBody = response.getBody();
            if (responseBody != null && responseBody.getResponse() != null) {
                List<Item> items = responseBody.getResponse().getBody().getItems().getItem();
                
                int i=0;
                for (Item item : items) {
                	System.out.println(i + " " + item);
                	i++;
                }
                return items;
            }
        }
		return null;
       
	}
}