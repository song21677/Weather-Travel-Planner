package com.olaenmanijo.weatherbasedtravelplanner.global.file.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.olaenmanijo.weatherbasedtravelplanner.global.file.controller.S3Uploader;
import com.olaenmanijo.weatherbasedtravelplanner.global.file.dto.request.TravelReviewFileRequest;
import com.olaenmanijo.weatherbasedtravelplanner.global.file.dto.response.TravelReviewFileResponse;
import com.olaenmanijo.weatherbasedtravelplanner.global.file.mapper.FileMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	private final FileMapper fileMapper;
	private final S3Uploader s3Uploader;

	@Transactional
	public void saveTravelReviewFiles(final Long travelReviewNo, final List<MultipartFile> imageFiles) {
		if (CollectionUtils.isEmpty(imageFiles)) {
			return;
		}

		for (MultipartFile file : imageFiles) {
			try {
				String imageUrl = s3Uploader.uploadFiles(file, "community"); // 이미지를 업로드하고 URL을 얻습니다.
				System.out.println("저장 경로 : " + imageUrl);
				System.out.println("원본 이름 : " + file.getOriginalFilename());
				System.out.println("파일 크기 : " + file.getSize());
				TravelReviewFileRequest travelReviewFileRequest = new TravelReviewFileRequest(
						file.getOriginalFilename(), imageUrl, file.getSize());
				travelReviewFileRequest.setTravelReviewNo(travelReviewNo);
				fileMapper.travelReviewImgSaveAll(travelReviewFileRequest);
			} catch (IOException e) {
				// 이미지 업로드 중 오류 처리
				e.printStackTrace();
				// 오류가 발생한 경우 예외 처리를 추가할 수 있습니다.
			}
		}

	}

	/**
	 * 파일 리스트 조회
	 * 
	 * @param travelReviewNo - 여행기 번호
	 * @return 파일 리스트
	 */
	public List<TravelReviewFileResponse> travelFileFindByTravelReviewNo(final Long travelReviewNo) {
		System.out.println("service : travelFileFindByTravelReviewNo");
		return fileMapper.travelFileFindByTravelReviewNo(travelReviewNo);
	}

	/**
	 * 파일 리스트 조회
	 * 
	 * @param fileNoes - PK 리스트
	 * @return 파일 리스트
	 */
	public List<TravelReviewFileResponse> travelFileFindByFileNoes(final List<Long> fileNoes) {
		if (CollectionUtils.isEmpty(fileNoes)) {
			return Collections.emptyList();
		}
		return fileMapper.travelFileFindByFileNoes(fileNoes);

	}

	/**
	 * 파일 삭제 (from Database)
	 * 
	 * @param ids - PK 리스트
	 */
	@Transactional
	public void deleteAllFileByIds(final List<Long> fileNoes) {
		if (CollectionUtils.isEmpty(fileNoes)) {
			return;
		}
		fileMapper.deleteByFileNoes(fileNoes);
	}

}
