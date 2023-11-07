package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherWithPlaceServiceImpl implements WeatherWithPlaceService {

	@Autowired
	WeatherWithPlaceDAO dao;
	
	private LocalDateTime dateTime = LocalDateTime.now();
	
	String checkday = null;
	
	public void getDetailPlan(int no){
		
		setColorBlock(dao.getDetailPlan(no));
		
	}
	
	public void setColorBlock(GetDetailPlanDTO dto) {
		String place_Category = dto.getPLACE_CATEGORY();
		int plan_No = dto.getDETAIL_PLAN_NO();
		String plan_Hour = dto.getDETAIL_PLAN_HOUR()+"00";
		String plan_Hour_end = dto.getDETAIL_PLAN_HOUR_END()+"00";
		String plan_Ymd = dto.getDETAIL_PLAN_YMD();
		String road_Name_Adr = dto.getROAD_NAME_ADR();
		String address = giveMeYourPlace(road_Name_Adr);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate planLocalDateTime = LocalDate.parse(plan_Ymd, formatter);
		// 날짜 차이 계산
		long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(planLocalDateTime, dateTime.toLocalDate());
		SetAddressDTO setdto = new SetAddressDTO();
		setdto.setAddress(address);
		setdto.setDate(plan_Ymd);
		setdto.setStartTime(plan_Hour);
		setdto.setEndTime(plan_Hour_end);
		if (daysBetween > 10) {
	    	defaultacculate(setdto,place_Category,plan_No,checkday);
        }else if(daysBetween >= 3) {
	    	withMediumWeather(setdto,place_Category,plan_No);
        }
	    else {
        	withShortWeather(setdto,place_Category,plan_No);
        }
	}
	
	
	// 행정 구역 추출기 ("xx시 xx구로 변환")
	public String giveMeYourPlace(String address) {
			String province = "";
		    String city = "";
		    String district = "";
		    String yourAddress = null;

		    String[] parts = address.split(" "); // 공백을 기준으로 주소를 분리

		    if (address.contains("도") && address.contains("시") && address.contains("구")) {
		        // "도" 와 "시"와 "구"가 모두 포함된 경우
		    	for (int i = 0; i < parts.length; i++) {
		    		if (parts[i].endsWith("도")) {
		    			province = parts[i];
		    		}
			        if (parts[i].endsWith("시")) {
			            city = parts[i];
			        }
			        if (parts[i].endsWith("구")) {
			            district = parts[i];
			        }
			        
			    }
		    	
		    	yourAddress = province + " " + city + " " + district;
		   
		    } else if (address.contains("도") && address.contains("시")) {
		        // "도"와 "시"가 모두 포함된 경우
		    	for (int i = 0; i < parts.length; i++) {
		    		if (parts[i].endsWith("도")) {
		    			province = parts[i];
		    		}
			        if (parts[i].endsWith("시")) {
			            city = parts[i];
			        }
		        }
		    	
		    	yourAddress = province + " " + city;
		    	
		    } else if (address.contains("시") && address.contains("구")) {
		        // "시"와 "구"가 모두 포함된 경우
		    	for (int i = 0; i < parts.length; i++) {
		    		if (parts[i].endsWith("시")) {
		    			province = parts[i];
		    		}
			        if (parts[i].endsWith("구")) {
			            district = parts[i];
			        }
			        
			        
			        yourAddress = province + " " + district;
		        }
		    } else if (address.contains("도") && address.contains("군")) {
		        // "도"와 "군"이 모두 포함된 경우
		    	for (int i = 0; i < parts.length; i++) {
		    		if (parts[i].endsWith("도")) {
		    			province = parts[i];
		    		}
			        if (parts[i].endsWith("군")) {
			            district = parts[i];
			        }
			        
			        		        
		        }
		    	
		    	  yourAddress = province + " " + district;
		    	
		    	
		    } else {
		        // 다른 형태의 주소는 분류할 수 없음
		    	System.out.println("분류할 수 없는 주소 형식입니다.");
		    }
		    
		    System.out.println("해당 행정구역은 " + yourAddress + "입니다");
		    
		    return yourAddress;
	}
	
	
	public void withShortWeather(SetAddressDTO dto,String category,int plan_No) {
		List<GetShortWeatherWithDTO> getdtos = dao.withShortWeather(dto);
		if(getdtos==null) {
			checkday = "checked";
			defaultacculate(dto,category,plan_No,checkday);
		}
		else {
			SetBlockDTO blockdto = new SetBlockDTO();
			blockdto.setPlan_No(plan_No);
			boolean isLastDto = false;
			int countyellow = 0;
			
			for (GetShortWeatherWithDTO getdto : getdtos) {
			    String day = getdto.getFORECAST_DAY();
			    String time = getdto.getFORECAST_TIME();
			    LocalDateTime times = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("HH00"));
			    int tmp = getdto.getTMP();
			    String pty = getdto.getPTY();
			    int pop = getdto.getPOP();
			    int reh = getdto.getREH();
				String pcp = getdto.getPCP();
				String sky = getdto.getSKY();
				int wsd = getdto.getWSD();
				int wav = getdto.getWAV();
				
				
				///////////최우선 로직
				if (category.equals("관광지") || category.equals("쇼핑") || category.equals("음식점") || category.equals("문화센터") || category.equals("레포츠") || category.equals("행사/공연/축제")) {
					if(times.getHour()>23 || times.getHour() <7) {
						blockdto.setReason(21);
						blockdto.setColor(3);
					}
				break;
				}
				
				if(category.equals("숙박")) {
					blockdto.setReason(1);
					blockdto.setColor(1);
				break;
				}	
				
				if(category.equals("음식점")) {
					blockdto.setReason(1);
					blockdto.setColor(1);
				break;
				}
				
				
				if(tmp<-10) {
					blockdto.setReason(11);
					blockdto.setColor(3);
				break;
				}
				if(tmp>32) {
					blockdto.setReason(12);
					blockdto.setColor(3);
				break;
				}
				
				if(wsd>14) {
					blockdto.setReason(13);
					blockdto.setColor(3);
				break;
				}
				if(wav>=2) {
					blockdto.setReason(14);
					blockdto.setColor(3);
				break;
				}
				if(pty.equals("1") || pty.equals("2")){
					blockdto.setReason(17);
					blockdto.setColor(3);
				break;	}
				
				if(pty.equals("3")){
					blockdto.setReason(18);
					blockdto.setColor(3);
				break;		
				}
				
				
				//////////////////////
				
				if(tmp>30){
					countyellow++;
				}
				if(tmp<-5) {
					countyellow++;
				}
				if(reh>95) {
					countyellow++;
				}
				if(reh<20) {
					countyellow++;
				}
				
				
				if(dto.getEndTime()==null || dto.getStartTime()==null) {
					if(countyellow>5) {
						blockdto.setColor(4);
						blockdto.setReason(201);
					}
					break;
				}
				
				isLastDto = (getdto.equals(getdtos.get(getdtos.size() - 1)));
				
				if (isLastDto && countyellow==0) {
				    blockdto.setColor(1);
				    blockdto.setReason(1);
				    break;
				}
				
				
			
			}
			if(countyellow>0) {
				blockdto.setColor(2);
				blockdto.setReason(31);
			}
			
			
			
			dao.SetBlock(blockdto);
			
			
			
		}
	};

	public void withMediumWeather(SetAddressDTO dto,String category,int plan_No) {
		List<GetMediumWeatherWithDTO> getdtos = dao.withMediumWeather(dto);
		if(getdtos==null) {
			checkday = "checked";
			defaultacculate(dto,category,plan_No,checkday);
		}
		else {
			SetBlockDTO blockdto = new SetBlockDTO();
			blockdto.setPlan_No(plan_No);
			boolean isLastDto = false;
			int countyellow = 0;
			for (GetMediumWeatherWithDTO getdto : getdtos) {
				
			 String day = getdto.getFORECAST_DAY();
			
			
			
			
			}
			
		}
		
	};
	
	public void defaultacculate(SetAddressDTO dto,String category,int plan_No,String checkday) {
		SetBlockDTO blockdto = new SetBlockDTO();
		blockdto.setPlan_No(plan_No);
		if(checkday=="checked") {
			blockdto.setReason(200);
			blockdto.setColor(4);
			dao.SetBlock(blockdto);
		}
		else {
		String date = dto.getDate().substring(4,2);
		switch (date) {
	    case "03":
	    case "04":
	        blockdto.setReason(101);
	        break;
	    case "07":
	    	blockdto.setReason(102);
	    	break;
	    case "08":
	    	blockdto.setReason(103);
	    	break;
	    case "12":
	    case "01":	
	    	blockdto.setReason(104);
	    	break;
	    case "06":
	    	blockdto.setReason(105);
	    	break;
	    default:
	    	blockdto.setReason(106); 
	        break;
		}
		
	    blockdto.setColor(4);
		dao.SetBlock(blockdto);
	}
		
	}
	
	
}
