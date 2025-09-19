package com.kfh.services;


import com.kfh.domains.Course;
import com.kfh.domains.Student;
import com.kfh.domains.StudentCourse;
import com.kfh.domains.StudentCourseId;
import com.kfh.dtos.StudentCourseRequest;
import com.kfh.repos.CourseRepository;
import com.kfh.repos.StudentCourseRepository;
import com.kfh.repos.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentCourseService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;

    @Transactional
    public void allocateCourses(StudentCourseRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Course> courses = courseRepository.findAllById(request.getCourseIds());
        if (courses.isEmpty()) {
            throw new RuntimeException("No valid courses");
        }

        for (Course course : courses) {
            StudentCourseId id = new StudentCourseId(student.getStudentId(), course.getCourseId());
            // avoid duplicates
            if (!studentCourseRepository.existsById(id)) {
                StudentCourse enrollment = StudentCourse.builder()
                        .id(id)
                        .student(student)
                        .course(course)
                        .enrolledAt(LocalDateTime.now())
                        .build();
                studentCourseRepository.save(enrollment);
            }
        }
    }

    @Transactional
    public void updateCoursesForStudent(StudentCourseRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Course> courses = courseRepository.findAllById(request.getCourseIds());

        // Add new
        for (Course course : courses) {
            StudentCourseId id = new StudentCourseId(student.getStudentId(), course.getCourseId());
            if (!studentCourseRepository.existsById(id)) {
                StudentCourse enrollment = StudentCourse.builder()
                        .id(id)
                        .student(student)
                        .course(course)
                        .enrolledAt(LocalDateTime.now())
                        .build();
                studentCourseRepository.save(enrollment);
            }
        }
    }
}
