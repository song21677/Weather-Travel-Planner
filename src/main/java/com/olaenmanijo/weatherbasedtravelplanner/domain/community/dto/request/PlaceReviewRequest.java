package com.olaenmanijo.weatherbasedtravelplanner.domain.community.dto.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class PlaceReviewRequest {

	Long placeReviewNo;
	Long detailPlanNo;
	Long memberNo;
	Long placeNo;
	String placeReviewImg;
	String placeReviewContent;
	Long placeReviewScore;

	// 파일 업로드 추가
//	List<MultipartFile> files = new ArrayList<>();

}
