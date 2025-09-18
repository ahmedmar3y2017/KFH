package com.kfh.rest;

import com.kfh.dtos.StudentCourseRequest;
import com.kfh.services.StudentCourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student-courses")
@RequiredArgsConstructor
public class StudentCourseController {

    private final StudentCourseService studentCourseService;
    // JWT Swagger Auth
    @SecurityRequirement(name = "Authorization")

    @PostMapping("/allocate")
    public ResponseEntity<String> allocateStudentToCourses(@RequestBody StudentCourseRequest request) {
        studentCourseService.allocateCourses(request);
        return ResponseEntity.ok("Student successfully allocated to selected courses");
    }
}
