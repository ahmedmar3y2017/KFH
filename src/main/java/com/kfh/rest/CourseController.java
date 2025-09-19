package com.kfh.rest;

import com.kfh.dtos.CourseDto;
import com.kfh.services.CourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    // JWT Swagger Auth
    @SecurityRequirement(name = "Authorization")
    @GetMapping
    public List<CourseDto> getAllCourses() {
        return courseService.getAllCourses();
    }
    // using reactive
    // JWT Swagger Auth
    @SecurityRequirement(name = "Authorization")
    @GetMapping("/reactive")
    public Flux<CourseDto> getAllCoursesReactive() {
        return courseService.getAllCoursesReactive();
    }
}
