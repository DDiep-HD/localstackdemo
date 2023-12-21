package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

  @Mock
  private StudentService studentService;

  @InjectMocks
  private StudentController studentController;

  @Test
  void whenGetStudentByIdExists_ReturnsStudent() {
    UUID id = UUID.randomUUID();
    Student expectedStudent = Student.builder().id(id).build();
    when(studentService.getStudent(id)).thenReturn(expectedStudent);

    Student actualStudent = studentController.getStudent(id);
    assertEquals(expectedStudent, actualStudent);
  }
}
