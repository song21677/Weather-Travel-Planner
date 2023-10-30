package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SpecialWeatherScheduler {
	
	@Autowired
	SpecialWeatherService service;

	private LocalDateTime dateTime;
	
	@Scheduled(cron = "0 30 * * * ?")
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
    	service.keepdateSpecialWeather();
        String fromTmFc = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String toTmFc = dateTime.plusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String type = "JSON";
        String numOfRows = "100";
        
        try {
			service.setWeatherData(fromTmFc, toTmFc, type, numOfRows);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        

        
     
    }
    
    public void getTrigger() {
    	scheduledTask();
	}
}
