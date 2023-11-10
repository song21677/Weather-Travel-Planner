package com.olaenmanijo.weatherbasedtravelplanner.global.file.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.olaenmanijo.weatherbasedtravelplanner.global.file.dto.request.TravelReviewFileRequest;


@Component
public class FileUtils {

	private final String uploadPath = Paths
			.get("C:", "gitFolder", "WeatherTravelPlanner", "src", "main", "resources", "static", "images", "upload")
			.toString();

	/**
	 * 다중 여행기 파일 업로드
	 * 
	 * @param multipartFiles - 파일 객체 List
	 * @return DB에 저장할 파일 정보 List
	 */
	public List<TravelReviewFileRequest> uploadTravelReviewFiles(final List<MultipartFile> multipartFiles) {
		List<TravelReviewFileRequest> files = new ArrayList<>();
		for (MultipartFile multipartFile : multipartFiles) {
			if (multipartFile.isEmpty()) {
				continue;
			}
			files.add(uploadTravelReviewFile(multipartFile));
		}
		return files;
	}

	/**
	 * 단일 여행기 파일 업로드
	 * 
	 * @param multipartFile - 파일 객체
	 * @return DB에 저장할 파일 정보
	 */
	public TravelReviewFileRequest uploadTravelReviewFile(final MultipartFile multipartFile) {

		if (multipartFile.isEmpty()) {
			return null;
		}

		String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
		String uploadPath = getUploadPath(today) + File.separator + saveName;
		File uploadFile = new File(uploadPath);

		try {
			multipartFile.transferTo(uploadFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return TravelReviewFileRequest.builder().originalName(multipartFile.getOriginalFilename()).saveName(saveName)
				.fileSize(multipartFile.getSize()).build();
	}

	/**
	 * 저장 파일명 생성
	 * 
	 * @param filename 원본 파일명
	 * @return 디스크에 저장할 파일명
	 */
	private String generateSaveFilename(final String filename) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String extension = StringUtils.getFilenameExtension(filename);
		return uuid + "." + extension;
	}

	/**
	 * 업로드 경로 반환
	 * 
	 * @return 업로드 경로
	 */
	private String getUploadPath() {
		return makeDirectories(uploadPath);
	}

	/**
	 * 업로드 경로 반환
	 * 
	 * @param addPath - 추가 경로
	 * @return 업로드 경로
	 */
	private String getUploadPath(final String addPath) {
		return makeDirectories(uploadPath + File.separator + addPath);
	}

	/**
	 * 업로드 폴더(디렉터리) 생성
	 * 
	 * @param path - 업로드 경로
	 * @return 업로드 경로
	 */
	private String makeDirectories(final String path) {
		File dir = new File(path);
		if (dir.exists() == false) {
			dir.mkdirs();
		}
		return dir.getPath();
	}

}
