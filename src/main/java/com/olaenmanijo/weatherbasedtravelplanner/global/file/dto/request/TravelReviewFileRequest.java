package com.olaenmanijo.weatherbasedtravelplanner.global.file.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class TravelReviewFileRequest {

	private Long fileNo; // 여행기 파일 번호 (PK)
	private String originalName; 	// 원본 파일명
	private String saveName; 		// 저장 파일명
	private long fileSize; 			// 파일 크기
	private Long travelReviewNo;    // 여행기 번호

	@Builder
	public TravelReviewFileRequest(String originalName, String saveName, long fileSize) {
		this.originalName = originalName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}
	
	 public void setTravelReviewNo(Long travelReviewNo) {
	        this.travelReviewNo = travelReviewNo;
	    }
	
}