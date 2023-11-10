package com.olaenmanijo.weatherbasedtravelplanner.global.file.controller;

import java.io.ByteArrayInputStream;
import java.io.File;

import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class CloudFileUpload {
	final String endPoint = "https://kr.object.ncloudstorage.com";
	final String regionName = "kr-standard";

    @Value("${naver.cloud.access}")
	String accessKey;
    @Value("${naver.cloud.secret}")
	String secretKey;
    
	public String cloudUpload(String uploadName, String filePath) {

		// S3 client
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.build();

		String bucketName = "team5-bucket";

		// create folder
		String folderName = "upload/";
		
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(0L);
		objectMetadata.setContentType("application/x-directory");
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName,
				new ByteArrayInputStream(new byte[0]), objectMetadata)
						.withCannedAcl(CannedAccessControlList.PublicRead);

		try {
			s3.putObject(putObjectRequest);
			System.out.format("Folder %s has been created.\n", folderName);
		} catch (AmazonS3Exception e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}

		// upload local file
		String objectName = folderName + uploadName;
		String uploadUrl = endPoint + '/' + bucketName + '/' + objectName;

		try {
			s3.putObject(new PutObjectRequest(bucketName, objectName, new File(filePath))
					.withCannedAcl(CannedAccessControlList.PublicRead));
			System.out.format("Object %s has been created.\n", objectName);
			
		} catch (AmazonS3Exception e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
		return uploadUrl;
	}
}
