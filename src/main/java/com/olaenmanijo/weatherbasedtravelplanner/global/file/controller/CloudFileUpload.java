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

    @Value("${naver.cloud.access}")
	private String accessKey;
    @Value("${naver.cloud.secret}")
	private String secretKey;
    @Value("${naver.cloud.regionName}")
    private String regionName;
    @Value("${naver.cloud.bucketName}")
    private String bucketName;
    @Value("${naver.cloud.endPoint}")
    private String endPoint;
    
	public String cloudUpload(String uploadName, File file) {

		// S3 client
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.build();


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
			s3.putObject(new PutObjectRequest(bucketName, objectName, file)
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
