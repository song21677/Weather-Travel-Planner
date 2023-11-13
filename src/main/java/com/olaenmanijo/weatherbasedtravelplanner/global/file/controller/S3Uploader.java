package com.olaenmanijo.weatherbasedtravelplanner.global.file.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class S3Uploader {
	private final AmazonS3 s3;
	@Value("${naver.cloud.bucketName}")
    private String bucketName;
	
	public String uploadFiles(MultipartFile multipartFile, String dirName) throws IOException {
		File uploadFile = convert(multipartFile) // 파일 변환할 수 없으면 에러
				.orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));
		return upload(uploadFile, dirName);
	}

	public String upload(File uploadFile, String filePath) {
		String fileName = filePath + "/" + UUID.randomUUID() + uploadFile.getName(); // S3에 저장된 파일 이름
		String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
		removeNewFile(uploadFile);
		return uploadImageUrl;
	}

	// amazonS3Client로 업로드
	private String putS3(File uploadFile, String fileName) {
		s3.putObject(new PutObjectRequest(bucketName, fileName, uploadFile)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		return s3.getUrl(bucketName, fileName).toString();
	}

//	 로컬에 저장된 이미지 지우기
	private void removeNewFile(File targetFile) {
		if (targetFile.delete()) {
			System.out.println("File delete success");
			return;
		}
		System.out.println("File delete fail");
	}

	// 로컬에 파일 업로드 하기
	private Optional<File> convert(MultipartFile file) throws IOException {
		System.out.println(file);
		File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
		System.out.println(convertFile);
		
		if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
			try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기
				System.out.println(fos);
				fos.write(file.getBytes());
			}
			return Optional.of(convertFile);
		}
		return Optional.empty();
	}
}
