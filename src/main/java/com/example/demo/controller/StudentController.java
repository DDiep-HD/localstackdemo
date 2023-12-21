package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

  private final StudentService studentService;

  @GetMapping("/{id}")
  public Student getStudent(@PathVariable UUID id) {
    log.info("Received request to get student with id={}", id);
    return studentService.getStudent(id);
  }
}
