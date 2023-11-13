package com.olaenmanijo.weatherbasedtravelplanner.WeatherWithPlace;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherWithPlaceServiceImpl implements WeatherWithPlaceService {

	@Autowired
	WeatherWithPlaceDAO dao;
	
	//현재날짜
	private LocalDateTime dateTime = LocalDateTime.now();
	
	//데이터 가져올때 문제가 생긴경우
	String checkday = null;
	
	//세부테이블 가져오기
	public ResponseEntity<Object> getDetailPlan(GetplanDTO getdto){
		GetDetailPlanDTO dto = dao.getDetailPlan(getdto);
		String start = getdto.getStartHour();
		String end = getdto.getEndHour();
		String date = getdto.getDate();
		if(dto==null) {
			System.out.println("해당값이 없어요");
			return null;
			}
		
		
		dto.setDETAIL_PLAN_HOUR(start);
		dto.setDETAIL_PLAN_HOUR_END(end);
		dto.setDETAIL_PLAN_YMD(date);
		return setColorBlock(dto);
		
	}
	
	//날짜 비교후 적합한 메서드에 제공(단기 , 중기 ,비교불가) 
	public ResponseEntity<Object> setColorBlock(GetDetailPlanDTO dto) {
		String place_Category = dto.getPLACE_CATEGORY();
		String plan_Hour = dto.getDETAIL_PLAN_HOUR();
		String plan_Hour_end = dto.getDETAIL_PLAN_HOUR_END();
		String plan_Ymd = dto.getDETAIL_PLAN_YMD();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate planLocalDateTime = LocalDate.parse(plan_Ymd, formatter);
		String road_Name_Adr = dto.getROAD_NAME_ADR();
		String address;
		try {
			address = giveMeYourPlace(road_Name_Adr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(address==null) {
			System.out.println("구역 분류중에 이상이 생겼나봐요!");
			return null;
		}
		
		/////이전 날짜일시 리턴
		if(plan_Hour!=null&&plan_Hour_end!=null) {
			if(planLocalDateTime.isBefore(dateTime.toLocalDate()) ) {
				System.out.println("지난 일은 수정이 불가합니다.");
				return null;
			}
		}
		if(planLocalDateTime.isBefore(dateTime.toLocalDate()) ||  (planLocalDateTime.isEqual(dateTime.toLocalDate()) && dateTime.getHour() >= Integer.parseInt(plan_Hour))){
			System.out.println("지난 일은 수정이 불가합니다.");
			return null;
		}
		
		
		//시간데이터 한개가 null일경우 return
		if (plan_Hour == null && plan_Hour_end == null) {
		} else {
		    if (plan_Hour == null || plan_Hour_end == null) {
		    	System.out.println("시간 값이 하나라서 로직을 실행할 수 없어요.");
		    	return null;
		    }
		}
		
		///
		
		//시간에 00플러스
		if(plan_Hour!=null&&plan_Hour_end!=null) {
			plan_Hour = plan_Hour +"00";
			plan_Hour_end = plan_Hour_end +"00";
		}
		
		// 날짜 차이 계산
		long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(dateTime.toLocalDate(),planLocalDateTime);
		SetAddressDTO setdto = new SetAddressDTO();
		setdto.setAddress(address);
		setdto.setDate(plan_Ymd);
		setdto.setStartTime(plan_Hour);
		setdto.setEndTime(plan_Hour_end);
		if (daysBetween > 10) {
	    	return defaultacculate(setdto,place_Category,checkday);
	    	
        }else if(daysBetween >= 3) {
	    	return withMediumWeather(setdto,place_Category);
        }
	    else {
        	return withShortWeather(setdto,place_Category);
        }
	}
	
	
	// 행정 구역 추출기 ("xx시 xx구로 변환")
	public String giveMeYourPlace(String address) throws Exception {
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
	
	
	public ResponseEntity<Object> withShortWeather(SetAddressDTO dto,String category) {
		List<GetShortWeatherWithDTO> getdtos = dao.withShortWeather(dto);
		
		///
		boolean nullpoint = false;
		String starttimes = dto.getStartTime();
		String endtimes = dto.getEndTime();
		List<LocalTime> hourlyDataList = new ArrayList<>();
		if(starttimes==null&& endtimes==null) {
			starttimes = "0000";
			endtimes= "2300";
			hourlyDataList.add(LocalTime.of(23, 0));
			nullpoint = true;
		}
				
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
		LocalTime starttime = LocalTime.parse(starttimes, formatter);
		LocalTime endtime = LocalTime.parse(endtimes, formatter);

		// 시작 시간부터 종료 시간까지 1시간 간격으로 정각 데이터 생성
		LocalTime currentTime = starttime;
		while (!currentTime.isAfter(endtime) && !currentTime.equals(endtime)) {
		    hourlyDataList.add(currentTime);
		    currentTime = currentTime.plusHours(1);
		}
	
		////
		
		if(getdtos==null) {
			checkday = "checked";
			return defaultacculate(dto,category,checkday);
		}
		else if (getdtos.size()!=24 || getdtos.size()!=hourlyDataList.size()) {
			checkday = "checked";
			return defaultacculate(dto,category,checkday);
		} 
			
		else {
			////
			
			
			////
			SetBlockDTO blockdto = new SetBlockDTO();
			boolean isLastDto = false;
			int countyellow = 0;
			
			for (GetShortWeatherWithDTO getdto : getdtos) {
			    String day = getdto.getFORECAST_DAY();
			    String time = getdto.getFORECAST_TIME();
			    LocalTime times = LocalTime.parse(time, formatter);
			    int tmp = getdto.getTMP();
			    String pty = getdto.getPTY();
			    int pop = getdto.getPOP();
			    int reh = getdto.getREH();
				String pcp = getdto.getPCP();
				String sky = getdto.getSKY();
				int wsd = getdto.getWSD();
				int wav = getdto.getWAV();
				
				///////////최우선 로직
				if (isPlaceCategory(category) && isLateNightTime(times) && nullpoint==false) {
				    blockdto.setReason(21);
				    blockdto.setColor("RD");
				    break;
				}

				
				if(category.equals("숙박")) {
					blockdto.setReason(1);
					blockdto.setColor("GN");
				break;
				}	
				
				if(category.equals("음식점")) {
					blockdto.setReason(1);
					blockdto.setColor("GN");
				break;
				}
				
				
				if(tmp<-10) {
					blockdto.setReason(11);
					blockdto.setColor("RD");
				break;
				}
				if(tmp>32) {
					blockdto.setReason(12);
					blockdto.setColor("RD");
				break;
				}
				
				if(wsd>14) {
					blockdto.setReason(13);
					blockdto.setColor("RD");
				break;
				}
				if(wav>=2) {
					blockdto.setReason(14);
					blockdto.setColor("RD");
				break;
				}
				if(pty.equals("1") || pty.equals("2")){
					blockdto.setReason(17);
					blockdto.setColor("RD");
				break;	}
				
				if(pty.equals("3")){
					blockdto.setReason(18);
					blockdto.setColor("RD");
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
				
				
				
				isLastDto = (getdto.equals(getdtos.get(getdtos.size() - 1)));
				
				if (isLastDto && countyellow==0) {
				    blockdto.setColor("GN");
				    blockdto.setReason(1);
				    break;
				}
				
				if(dto.getEndTime()==null || dto.getStartTime()==null) {
					if(countyellow>5) {
						blockdto.setColor("GY");
						blockdto.setReason(31);
					}
				}
				
			
			}//for each end
			
			if(countyellow>0 && blockdto.getColor()==null) {
				blockdto.setColor("YL");
				blockdto.setReason(31);
			}
			
			if(blockdto.getColor()==null) {
				blockdto.setReason(200);
				blockdto.setColor("GY");
			}
			
						
			return new ResponseEntity<>(blockdto, HttpStatus.OK);
			
			
		}
	};

	public ResponseEntity<Object> withMediumWeather(SetAddressDTO dto,String category) {
		GetMediumWeatherWithDTO getdto = dao.withMediumWeather(dto);
		if(getdto==null) {
			checkday = "checked";
			System.out.println("getdto가 값이 null입니다.");
			return defaultacculate(dto,category,checkday);
		}
		else {
			SetBlockDTO blockdto = new SetBlockDTO();
			boolean isLastDto = false;
			int countyellow = 0;
			String starttimes = dto.getStartTime();
			String endtimes = dto.getEndTime();
			
			if(starttimes==null && endtimes==null) {
				starttimes = "1100";
				endtimes = "1400";
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
			LocalTime starttime = LocalTime.parse(starttimes, formatter);
			LocalTime endtime = LocalTime.parse(endtimes, formatter);
			 
			List<LocalTime> hourlyDataList = new ArrayList<>();

	        // 시작 시간부터 종료 시간까지 1시간 간격으로 정각 데이터 생성
			LocalTime currentTime = starttime;
			while (!currentTime.isAfter(endtime) && !currentTime.equals(endtime)) {
			    hourlyDataList.add(currentTime);
			    currentTime = currentTime.plusHours(1);
			}
			String day = getdto.getFORECAST_DAY();
			int rnst = 0;
			int ta = 0;
			String wf = null;
			
			for (LocalTime times : hourlyDataList) {
				
				if(times.getHour()>12) {
					rnst = getdto.getRNSTPM();
					ta = getdto.getTAMAX();
					wf = getdto.getWFPM();
					
				}else {
					rnst = getdto.getRNSTAM();
					ta = getdto.getTAMIN();
					wf = getdto.getWFAM();
				}
					///////////최우선 로직
					if (isPlaceCategory(category) && isLateNightTime(times)) {
				    blockdto.setReason(21);
				    blockdto.setColor("RD");
				    break;
					}
					
					if(category.equals("숙박")) {
						blockdto.setReason(1);
						blockdto.setColor("GN");
					break;
					}	
					
					if(category.equals("음식점")) {
						blockdto.setReason(1);
						blockdto.setColor("GN");
					break;
					}
					
					
					if(ta<-10) {
						blockdto.setReason(11);
						blockdto.setColor("RD");
					break;
					}
					if(ta>32) {
						blockdto.setReason(12);
						blockdto.setColor("RD");
					break;
					}
					
					if(wf.contains("비")) {
						blockdto.setReason(17);
						blockdto.setColor("YL");
						countyellow++;
					}
					if(wf.contains("눈")) {
						blockdto.setReason(18);
						blockdto.setColor("YL");
						countyellow++;
					}
					//////////////////////
					
					if(ta>30){
						countyellow++;
					}
					if(ta<-5) {
						countyellow++;
					}
					if(rnst>80) {
						countyellow++;
					}
					
					
										
					isLastDto = times.equals(endtime.minusHours(1));
					if (isLastDto && countyellow==0) {
					    blockdto.setColor("GN");
					    blockdto.setReason(1);
					}
			}
					
				
				if(countyellow>0) {
					blockdto.setColor("YL");
					blockdto.setReason(31);
				}
				
				if(blockdto.getColor()==null) {
					blockdto.setReason(200);
					blockdto.setColor("GY");
				}
				
				
				return new ResponseEntity<>(blockdto, HttpStatus.OK);
			}
			
			
			
		
	};
	
	public ResponseEntity<Object> defaultacculate(SetAddressDTO dto,String category,String checkday) {
		SetBlockDTO blockdto = new SetBlockDTO();
		if(checkday=="checked") {
			blockdto.setReason(200);
			blockdto.setColor("GY");
			
		}
		else {
			
			
	String starttimes = dto.getStartTime();
	String endtimes = dto.getEndTime();
	if(starttimes==null && endtimes==null) {
		starttimes = "1100";
		endtimes = "1300";
	}

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
	LocalTime starttime = LocalTime.parse(starttimes, formatter);
	LocalTime endtime = LocalTime.parse(endtimes, formatter);
	List<LocalTime> hourlyDataList = new ArrayList<>();

    // 시작 시간부터 종료 시간까지 1시간 간격으로 정각 데이터 생성
	LocalTime currentTime = starttime;
    while (!currentTime.isAfter(endtime)) {
        hourlyDataList.add(currentTime);
        currentTime = currentTime.plusHours(1);
    }		
		for (LocalTime times : hourlyDataList) {
			
	///////////최우선 로직
	if (isPlaceCategory(category) && isLateNightTime(times)) {
	    blockdto.setReason(21);
	    blockdto.setColor("RD");
	    break;
	}

	
	if(category.equals("숙박")) {
		blockdto.setReason(1);
		blockdto.setColor("GN");
	break;
	}	
	
	if(category.equals("음식점")) {
		blockdto.setReason(1);
		blockdto.setColor("GN");
	break;
	}	

		}
		if(blockdto.getColor()==null) {
		String date = dto.getDate().substring(4,6);
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
		
		
		blockdto.setColor("GY");
		}
		
		if(blockdto.getColor()==null) {
			blockdto.setReason(200);
			blockdto.setColor("GY");
		}
		
	    
	}
		return new ResponseEntity<>(blockdto, HttpStatus.OK);
		
	}
	
	
	
	
	private boolean isPlaceCategory(String category) {
	    return category.equals("관광지") || 
	           category.equals("쇼핑") || 
	           category.equals("음식점") || 
	           category.equals("문화센터") || 
	           category.equals("레포츠") || 
	           category.equals("행사/공연/축제");
	}

	private boolean isLateNightTime(LocalTime times) {
	    int hour = times.getHour();
	    return hour > 23 || hour < 7;
	}


	
	
}
