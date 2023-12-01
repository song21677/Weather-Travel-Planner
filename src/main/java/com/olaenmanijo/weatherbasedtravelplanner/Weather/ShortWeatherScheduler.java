package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

@Component
public class ShortWeatherScheduler {
	private final Object lock = new Object();

	@Autowired
    private ShortWeatherService weatherService;

	@Autowired
    private GetDataService getdataService;
	
    private LocalDateTime dateTime;
    
    @Scheduled(cron = "0 0 3,6,9,12,15,18,21,0 * * ?")
    public void scheduledTask() {
        setDateTime();
        executeTask();
    }
    
    public void setDateTime() {
    	 // 현재 시간을 기준으로 1시간 전의 시간을 설정합니다.
        LocalDateTime now = LocalDateTime.now();
        if (now.getHour() == 0) {
            dateTime = now.minusDays(1).withHour(23).withMinute(0).withSecond(0).withNano(0);
        } else {
            dateTime = now.minusHours(1).withMinute(0).withSecond(0).withNano(0);
        }
    }
    
	

    
    public void executeTask() {
        
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
        Iterator<Second_precinct_tb_DTO> iterator = dataList.iterator();
        
        int index = 0;
        while (iterator.hasNext()) {
        	index++;
        	Second_precinct_tb_DTO data = iterator.next();
        	
        	int second_Precinct_No = data.getSecond_Precinct_No();
        	String nx = Integer.toString(data.getNx());
            String ny = Integer.toString(data.getNy());

            try {
                weatherService.getWeatherData(nx, ny, baseDate, baseTime, type, numOfRows,second_Precinct_No);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            synchronized (lock) {
                if (index % 50 == 0) { // 트랜잭션 숫자 조절 기능
                    weatherService.submitDataStore();
                    weatherService.claerDataStore();
                }
                if(!iterator.hasNext()) {
	                try {
	                    weatherService.submitDataStore();
	                    System.out.println("끝");
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
            }
            }
        }

    }
    
    public void getTrigger() {
		LocalDateTime now = LocalDateTime.now();
		if (now.getHour() >= 21) {
	        dateTime = now.withHour(20).withMinute(0).withSecond(0).withNano(0);
	    } else if (now.getHour() >= 18) {
	        dateTime = now.withHour(17).withMinute(0).withSecond(0).withNano(0);
	    } else if (now.getHour() >= 15) {
	        dateTime = now.withHour(14).withMinute(0).withSecond(0).withNano(0);
	    } else if (now.getHour() >= 12) {
	        dateTime = now.withHour(11).withMinute(0).withSecond(0).withNano(0);
	    } else if (now.getHour() >= 9) {
	        dateTime = now.withHour(8).withMinute(0).withSecond(0).withNano(0);
	    } else if (now.getHour() >= 6) {
	        dateTime = now.withHour(5).withMinute(0).withSecond(0).withNano(0);
	    } else if (now.getHour() >= 3) {
	        dateTime = now.withHour(2).withMinute(0).withSecond(0).withNano(0);
	    } else if (now.getHour() >= 0) {
	    	dateTime = now.minusDays(1).withHour(23).withMinute(0).withSecond(0).withNano(0);
	    } 
		
		executeTask();
	}

}





