package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class MediumWeatherScheduler {
	
	@Autowired
	MediumWeatherService weatherService;
	
	@Autowired
	GetDataService getdataService;
	
	private LocalDateTime dateTime;

	@Scheduled(cron = "0 0 7,19 * * ?")
    public void scheduledTask() {
        setDateTime();
        executeTask();
    }
    
    public void setDateTime() {
        // 현재 시간을 기준으로 1시간 전의 시간을 설정합니다.
        LocalDateTime now = LocalDateTime.now();
        dateTime = now.minusHours(1).withMinute(0).withSecond(0).withNano(0);
    }
    
	
	public void executeTask() {
		
		//발표시각이 3개 이상일경우 삭제
		weatherService.keepdateTemp();
		
		String tmFc = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
		//발표시각 데이터 가져오기
		List<String> gettmFcs = weatherService.findMediumAnn();
		for (String gettmFc : gettmFcs) {
			if(gettmFc.contentEquals(tmFc))
		return;
		}
        
        String type = "JSON";
        String numOfRows = "100";
        List<Second_precinct_tb_DTO> dataList = getdataService.getSecondPrecinctData();
        for (Second_precinct_tb_DTO dto : dataList) {
			String regId=dto.getSecond_Precinct_Cd();
			int no = dto.getSecond_Precinct_No();
			int no2 = dto.getFirst_Precinct_No();
			try {
                weatherService.getWeatherData1(tmFc, type, numOfRows,regId,no,no2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        
        List<First_precinct_tb_DTO> dataList2 = getdataService.getFirstPrecinctData();
        for (First_precinct_tb_DTO dto : dataList2) {
			String regid = dto.getFIRST_PRECINCT_CD();
			int no = dto.getFIRST_PRECINCT_NO();
			try {
                weatherService.getWeatherData2(tmFc, type, numOfRows,regid,no);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}

        
        
        

    }
	
	public void getTrigger() {
		LocalDateTime now = LocalDateTime.now();
  	    if (now.getHour() >= 19) {
  	        dateTime = now.withHour(18).withMinute(0).withSecond(0).withNano(0);
  	    } else {
  	        dateTime = now.withHour(06).withMinute(0).withSecond(0).withNano(0);
  	    }
  	    
		executeTask();
	}
	
	


}
