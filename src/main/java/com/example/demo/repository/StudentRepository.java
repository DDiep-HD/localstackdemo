package com.example.demo.repository;

import com.example.demo.model.Student;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest;

@Repository
@RequiredArgsConstructor
@Slf4j
public class StudentRepository {

  private final DynamoDbEnhancedClient client;

  public Student getStudent(UUID id) {
    log.info("Getting student with id={}", id);
    DynamoDbTable<Student> table = client.table("Student", TableSchema.fromBean(Student.class));
    Key key = Key.builder().partitionValue(id.toString()).build();
    return table.getItem((GetItemEnhancedRequest.Builder requestBuilder) -> requestBuilder.key(key));
  }
}
