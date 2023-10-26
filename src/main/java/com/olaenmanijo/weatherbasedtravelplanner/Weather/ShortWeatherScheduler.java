package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@EnableScheduling
public class ShortWeatherScheduler {
	

	@Autowired
    private ShortWeatherService weatherService;

	@Autowired
    private GetDataService getdataService;
	
    private LocalDateTime dateTime;

    @Scheduled(cron = "0 0 3,6,9,12,15,18,21,0 * * ?")
    public void scheduledTask() {
        // 현재 시간을 기준으로 1시간 전의 시간을 설정합니다.
        LocalDateTime now = LocalDateTime.now();
        if (now.getHour() == 0) {
            dateTime = now.minusDays(1).withHour(23).withMinute(0).withSecond(0).withNano(0);
        } else {
            dateTime = now.minusHours(1).withMinute(0).withSecond(0).withNano(0);
        }

        
        String baseDate = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseTime = dateTime.format(DateTimeFormatter.ofPattern("HH00"));
        String type = "JSON";
        String numOfRows = "1000";
        
        //발표시각이 이미 동일한 값이 있을경우 중복 삽입 방지 
        List<ShortTermAnnounceDTO> shortTermAnnounceDTOs = weatherService.findAll();
        boolean isDataAlreadyExists = false;
        for (ShortTermAnnounceDTO dto : shortTermAnnounceDTOs) {
            if (baseDate.equals(dto.getANNOUNCE_DAY()) && baseTime.equals(dto.getANNOUNCE_TIME())) {
                isDataAlreadyExists = true;
                break;
            }
        }

        if (isDataAlreadyExists) {
            return; // 이미 해당 데이터가 있으므로, 나머지 로직을 중지합니다.
        }
       //행이 3개이상일시 번호가 낮은순으로 삭제
        weatherService.deleteExtraRows();
        
        
        List<Second_precinct_tb_DTO> dataList = getdataService.getSecondPrecinctData();
        
        int index = 0;
        for (Second_precinct_tb_DTO data : dataList) {
            index++;

        	int second_Precinct_No = data.getSecond_Precinct_No();
        	String nx = Integer.toString(data.getNx());
            String ny = Integer.toString(data.getNy());

            try {
                weatherService.getWeatherData(nx, ny, baseDate, baseTime, type, numOfRows,second_Precinct_No);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (index % 50 == 0) { // 트랜잭션 숫자 조절 기능
                	Thread.sleep(1 * 5 * 1000); // 5초 동안 스레드를 잠시 중지합니다.
                	weatherService.submitDataStore();
                    Thread.sleep(1 * 20 * 1000); //50초 동안 스레드를 잠시 중지합니다.
                    weatherService.claerDataStore();
                    Thread.sleep(1 * 5 * 1000); // 5초 동안 스레드를 잠시 중지합니다.
                } else {
                    Thread.sleep(1000); // 쿼리 호출 시 매번1초 동안 스레드를 잠시 중지합니다. 
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            weatherService.submitDataStore();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}





