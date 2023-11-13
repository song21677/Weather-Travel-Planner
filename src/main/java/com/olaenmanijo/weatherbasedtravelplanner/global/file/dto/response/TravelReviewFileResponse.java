package com.olaenmanijo.weatherbasedtravelplanner.global.file.dto.response;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class TravelReviewFileResponse {

	private Long fileNo; // 여행기 파일 번호 (PK)
	private String originalName; // 원본 파일명
	private String saveName; // 저장 파일명
	private long fileSize; // 파일 크기
	private String deleteYN;
	private String createdDate;
	private String deletedDate;
	private Long travelReviewNo; // 여행기 번호

}