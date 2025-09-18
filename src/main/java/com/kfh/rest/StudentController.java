package com.kfh.rest;

import com.kfh.dtos.CourseDto;
import com.kfh.dtos.StudentDto;
import com.kfh.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {


    private final StudentService studentService;


    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }
}
