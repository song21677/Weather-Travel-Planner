package com.olaenmanijo.weatherbasedtravelplanner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
	@Value("${naver.cloud.access}")
	private String accessKey;
	@Value("${naver.cloud.secret}")
	private String secretKey;
	@Value("${naver.cloud.regionName}")
	private String regionName;
    @Value("${naver.cloud.endPoint}")
    private String endPoint;
	
	
	@Bean
		public AmazonS3 s3() {
	        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,secretKey);
	        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
					.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
					.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
					.build();
	        return s3;
	    }
		
	}

