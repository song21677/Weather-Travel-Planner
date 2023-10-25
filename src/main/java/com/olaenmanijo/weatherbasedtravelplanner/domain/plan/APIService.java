package com.olaenmanijo.weatherbasedtravelplanner.domain.plan;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
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
	String subUrl = "/B551011/KorService1/areaBasedList1";
	String serviceKey = "xSNjOLnnZKF%2B4zEt6Y7%2Bk79dqA76nW9NSDdStY2MsFcvF3zkLs1gLGOAyg5bVWoJhBT8BQeRPsY%2FRYsPu2Ukfg%3D%3D";
	public void requestToTourListBasedAreaAPI(SearchForm searchForm) throws IOException, URISyntaxException {
		 UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + subUrl)
		            .queryParam("numOfRows", 10)
		            .queryParam("pageNo", 1)
		            .queryParam("MobileOS", "ETC")
		            .queryParam("MobileApp", "WeatherPlan")
		            .queryParam("serviceKey", serviceKey) // 이미 URL 인코딩된 값
		            .queryParam("areaCode", searchForm.getArea())
		            .queryParam("contentTypeId", searchForm.getCategory())
		            .queryParam("_type", "json");

		URI uri = builder.build().toUri();
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
                for (Item item : items) {
                    String title = item.getTitle();
                    String addr1 = item.getAddr1();
                    System.out.println("Title: " + title);
                    System.out.println("Address: " + addr1);
                }
            }
        }
       
	}
}