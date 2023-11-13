package com.olaenmanijo.weatherbasedtravelplanner.global.file.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.olaenmanijo.weatherbasedtravelplanner.global.file.dto.response.TravelReviewFileResponse;
import com.olaenmanijo.weatherbasedtravelplanner.global.file.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileApiController {
	private final FileService fileService;
	
	// 파일 리스트 조회
    @GetMapping("/communities/{travelReviewNo}/files")
    @ResponseBody
    public List<TravelReviewFileResponse> findAllFileByPostId(@PathVariable final Long travelReviewNo) {
        return fileService.travelFileFindByTravelReviewNo(travelReviewNo);
    }
    
    // 파일 리스트 저장
    @PostMapping("/communities/{travelReviewNo}/files")
	@ResponseBody
	public void uploadImages(@PathVariable final Long travelReviewNo, @RequestParam("imageFiles") List<MultipartFile> imageFiles) {
		fileService.saveTravelReviewFiles(travelReviewNo, imageFiles);
	}
    
    @GetMapping("/travelReviewFile-count/{travelReviewNo}")
	@ResponseBody
	public Long filecountBytravelReviewNo(@PathVariable final Long travelReviewNo) {
    	Long travelReviewFileCount = (long) fileService.travelFileFindByTravelReviewNo(travelReviewNo).size();
    	System.out.println("travelReviewFileCount :" + travelReviewFileCount);
		return travelReviewFileCount;
	}

}
