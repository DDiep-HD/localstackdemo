package com.example.demo.config;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDbTestConfig {

  @Value("${aws.dynamodb.endpoint}")
  String url;

  @Value("${aws.accesskey:mock}")
  String mockAccessKey;

  @Value("${aws.secretkey:mock}")
  String mockSecretKey;


  @Profile({"test", "component"})
  @Bean
  DynamoDbClient TestDdb() {
    return DynamoDbClient.builder()
        .region(Region.EU_WEST_2)
        .endpointOverride(URI.create(url))
        .credentialsProvider(StaticCredentialsProvider.create(
            AwsBasicCredentials.create(mockAccessKey, mockSecretKey)))
        .build();
  }
}
