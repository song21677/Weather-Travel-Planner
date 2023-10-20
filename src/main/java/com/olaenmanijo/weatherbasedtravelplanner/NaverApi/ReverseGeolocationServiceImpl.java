package com.olaenmanijo.weatherbasedtravelplanner.NaverApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReverseGeolocationServiceImpl implements ReverseGeolocationService {

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, String> getAddress(double lat, double lon, HttpSession session) throws IOException {
        Double storedLat = (Double) session.getAttribute("lastLat");
        Double storedLon = (Double) session.getAttribute("lastLon");
        String storedAddress = (String) session.getAttribute("lastAddress");

        if (storedLat != null && storedLon != null && storedAddress != null && storedLat.equals(lat) && storedLon.equals(lon)) {
            Map<String, String> result = new HashMap<>();
            result.put("address", storedAddress);
            return result;
        } else {
            String apiUrl = String.format("https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?request=coordsToaddr&coords=%f,%f&sourcecrs=epsg:4326&orders=admcode,legalcode,addr,roadaddr&output=json", lon, lat);
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
            headers.set("X-NCP-APIGW-API-KEY", clientSecret);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            String responseBody = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class).getBody();

            JsonNode jsonResponse = objectMapper.readTree(responseBody);
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

            return result;
        }
    }
}
