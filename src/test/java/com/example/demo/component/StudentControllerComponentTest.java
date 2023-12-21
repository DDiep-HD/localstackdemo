package com.example.demo.component;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.Student;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

public class StudentControllerComponentTest extends ComponentTest {
  @Test
  void whenGetStudentExists_thenReturnStudent() throws Exception {
    UUID id = UUID.randomUUID();

    DynamoDbTable<Student> studentTable = ddbClient.table("Student", TableSchema.fromBean(Student.class));
    Student expectedStudent = Student.builder().id(id).firstName("Foo").secondName("Bar").build();
    studentTable.putItem(expectedStudent);

    mockMvc.perform(get("/student/" + id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id.toString()))
        .andExpect(jsonPath("$.firstName").value(expectedStudent.getFirstName()))
        .andExpect(jsonPath("$.secondName").value(expectedStudent.getSecondName()));
  }
}
