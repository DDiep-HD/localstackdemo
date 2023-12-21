package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDbConfig {

  @Profile({"dev", "qa", "uat", "prod"})
  @Bean
  DynamoDbClient ddb() {
    return DynamoDbClient.builder()
        .region(Region.EU_WEST_2)
        .build();
  }

  @Bean
  DynamoDbEnhancedClient enhancedClient(DynamoDbClient ddb) {
    return DynamoDbEnhancedClient.builder()
        .dynamoDbClient(ddb)
        .build();
  }
}
