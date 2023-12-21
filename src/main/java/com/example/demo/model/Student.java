package com.example.demo.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@Builder
@DynamoDbBean
@AllArgsConstructor
@NoArgsConstructor
public class Student {
  private UUID id;
  private String firstName;
  private String secondName;

  @DynamoDbPartitionKey
  public UUID getId() {
    return this.id;
  }
}
