package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


//리스트를 임시 저장하기위한 DTO - 트랜잭션에서 사용
@Component
public class MediumWeatherDataStore {

    private final List<ShortTermForecastDTO> forecastDTOList = new ArrayList<>();
    private ShortTermAnnounceDTO announceDTO = null;  // 초기값은 null로 설정


    public boolean isAnnounceDataEmpty() {
        return announceDTO == null;
    }
    
    public void insertAnnounceData(ShortTermAnnounceDTO dto) {
        this.announceDTO = dto;
    }

    public void addForecastData(ShortTermForecastDTO dto) {
        forecastDTOList.add(dto);
    }


    public List<ShortTermForecastDTO> getForecastDTOList() {
        return forecastDTOList;
    }

    public ShortTermAnnounceDTO getAnnounceDTO() { 
        return announceDTO;
    }
}