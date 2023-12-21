package com.example.demo.component;

import com.example.demo.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.localstack.LocalStackContainer.Service;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("component")
@Testcontainers
public class ComponentTest {
  @Container
  protected static final LocalStackContainer localStackContainer =
      new LocalStackContainer(DockerImageName.parse("localstack/localstack:1.3.1"))
          .withServices(Service.DYNAMODB);

  @DynamicPropertySource
  static void registerDynamicProperties(DynamicPropertyRegistry registry) {
    registry.add("aws.dynamodb.endpoint", () -> localStackContainer.getEndpointOverride(LocalStackContainer.Service.DYNAMODB));
    registry.add("aws.accesskey", () -> "testAccessKey");
    registry.add("aws.secretkey", () -> "testSecretKey");
  }

  @Autowired
  protected MockMvc mockMvc;
  @Autowired
  DynamoDbEnhancedClient ddbClient;

  @BeforeAll
  static void setupLocalStack() {
    localStackContainer.start();
  }

  @BeforeEach
  void setupDynamoDb() {
    // Create DynamoDB table
    DynamoDbTable<Student> customerTable = ddbClient.table("Student", TableSchema.fromBean(Student.class));
    customerTable.createTable(builder -> builder
        .provisionedThroughput(ProvisionedThroughput.builder()
            .readCapacityUnits(10L)
            .writeCapacityUnits(10L)
            .build()));
  }
}
