package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


@Service
public class ShortWeatherServiceImpl implements ShortWeatherService {
	
	
	@Autowired
	ShortWeatherDAO dao;
	
	@Autowired
    ShortWeatherDataStore dataStore;

    @Value("${shortweather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String serviceKey;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void getWeatherData(String nx, String ny, String baseDate, String baseTime, String type, String numOfRows,int second_Precinct_No) throws Exception {

        String requestUrl = buildRequestUrl(nx, ny, baseDate, baseTime, type, numOfRows);
        URI uri = new URI(requestUrl);
        
        System.out.println(uri);
        String response = restTemplate.getForObject(uri, String.class);
        
        if (response.contains("<OpenAPI_ServiceResponse>")) {
            // API 응답이 XML인 경우
            return;
        }

                      
        // JSON 파싱 및 데이터 추출
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");
        
        

        
     // dataStore에 AnnounceData가 없는 경우에만 저장
        // 266번 반복해서 쿼리 호출하기때문에 방지위해 삽입
        // 효용성 있는지는 잘 모르겠음
        if (dataStore.isAnnounceDataEmpty()) {
        	ShortTermAnnounceDTO announceDTO = new ShortTermAnnounceDTO();
        	announceDTO.setANNOUNCE_DAY(baseDate);
        	announceDTO.setANNOUNCE_TIME(baseTime);
            dataStore.insertAnnounceData(announceDTO);
            
            }
        
        String prevFcstTime = null;
        ShortTermForecastDTO forecastDTO = new ShortTermForecastDTO();
        
        for (JsonNode item : itemsNode) {
            String currentFcstTime = item.path("fcstTime").asText();

            // fcstTime이 변경되면 이전 DTO를 저장하고 새로운 DTO를 시작
            //JSON형태로 모든 시간대로 길게 나오기때문에 끊을 지점 선정
            if (prevFcstTime != null && !currentFcstTime.equals(prevFcstTime)) {
                dataStore.addForecastData(forecastDTO);
                forecastDTO = new ShortTermForecastDTO();
            }

            
            forecastDTO.setSECOND_PRECINCT_NO(second_Precinct_No); 
            forecastDTO.setFORECAST_TIME(currentFcstTime);
            forecastDTO.setFORECAST_DAY(item.path("fcstDate").asText());
        
        String category = item.path("category").asText();
        int value = item.path("fcstValue").asInt();
        String value2 = item.path("fcstValue").asText();

        switch (category) {
        case "TMP":
            forecastDTO.setTMP(value);
            break;
        case "PTY":
            forecastDTO.setPTY(value2);
            break;
        case "POP":
            forecastDTO.setPOP(value);
            break;
        case "REH":
            forecastDTO.setREH(value);
            break;
        case "PCP":
            forecastDTO.setPCP(value2);
            break;
        case "SKY":
            forecastDTO.setSKY(value2);
            break;
        case "WSD":
            forecastDTO.setWSD(value);
            break;
        case "WAV":
            forecastDTO.setWAV(value);
            break;
        }
        
        prevFcstTime = currentFcstTime;
        
    }
        // DTO를 데이터베이스에 저장합니다.
        dataStore.addForecastData(forecastDTO);
        
 }
    
    //쿼리 작성기
    private String buildRequestUrl(String nx, String ny, String baseDate, String baseTime, String type, String numOfRows) throws Exception {
        return new StringBuilder(apiUrl)
                .append("?serviceKey=").append(URLEncoder.encode(serviceKey,"UTF-8"))
                .append("&pageNo=").append(URLEncoder.encode("1", "UTF-8"))
                .append("&numOfRows=").append(URLEncoder.encode(numOfRows, "UTF-8"))
                .append("&dataType=").append(URLEncoder.encode(type, "UTF-8"))
                .append("&base_date=").append(URLEncoder.encode(baseDate, "UTF-8"))
                .append("&base_time=").append(URLEncoder.encode(baseTime, "UTF-8"))
                .append("&nx=").append(URLEncoder.encode(nx, "UTF-8"))
                .append("&ny=").append(URLEncoder.encode(ny, "UTF-8"))
                .toString();
    }

    //리스트 초기화용
	@Override
	public void claerDataStore() {
		dataStore.clear();
	}
	
	
	//ShortTermAnnounceDTO같은 경우는 한번만 삽입 하면 되기때문에 중복 처리를 방지하고자 체킹삽입.(트랜잭션을 끊어서 스케줄러에서 여러번 발송하기 때문)
	//결과적으로 currentAnnounceDTO 1번과 forecast 스케줄러 설정만큼 끊어서 db에 삽입됨.
	private ShortTermAnnounceDTO lastSavedAnnounceDTO = null;
	@Transactional
	@Override
	public void submitDataStore() {
	    // WeatherDataStore에서 저장된 리스트를 가져옵니다.
	    List<ShortTermForecastDTO> forecastDTOs = dataStore.getForecastDTOList();
	    ShortTermAnnounceDTO currentAnnounceDTO = dataStore.getAnnounceDTO();
	    
	    // lastSavedAnnounceDTO와 현재의 announceDTO를 비교
	    if (!currentAnnounceDTO.equals(lastSavedAnnounceDTO)) {
	        dao.insertAnnounceData(currentAnnounceDTO);
	        lastSavedAnnounceDTO = currentAnnounceDTO;
	    }

	    for (ShortTermForecastDTO forecastDTO : forecastDTOs) {
	        dao.insertForecastData(forecastDTO);
	    }
	}
	
	
	//발표시각이 4개 이상일 경우 낮은순서부터 삭제
	public void deleteExtraRows() {
		dao.deleteLowestIDX();
	}
	
	public List<ShortTermAnnounceDTO> findAll() {
		return dao.findAll();
	}


	
}