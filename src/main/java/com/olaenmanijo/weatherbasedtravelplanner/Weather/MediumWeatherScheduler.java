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
	    // 현재 시간을 기준으로 1시간 전의 시간을 설정합니다.
	    LocalDateTime now = LocalDateTime.now();
	        dateTime = now.minusHours(1).withMinute(0).withSecond(0).withNano(0);
        
        String tmFc = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String type = "JSON";
        String numOfRows = "100";
        List<Second_precinct_tb_DTO> dataList = getdataService.getSecondPrecinctData();
        for (Second_precinct_tb_DTO dto : dataList) {
			String regId=dto.getSecond_Precinct_Cd();
			int no = dto.getSecond_Precinct_No();
			try {
                weatherService.getWeatherData1(tmFc, type, numOfRows,regId,no);
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
        //발표시각이 이미 동일한 값이 있을경우 중복 삽입 방지 
      /*  List<ShortTermAnnounceDTO> shortTermAnnounceDTOs = weatherService.findAll();
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
        */
        
        
        

    }


}
