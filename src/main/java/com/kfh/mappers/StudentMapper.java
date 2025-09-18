package com.kfh.mappers;


import com.kfh.domains.Course;
import com.kfh.domains.Student;
import com.kfh.dtos.CourseDto;
import com.kfh.dtos.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentCourseMapper.class})
public interface StudentMapper {
    @Mapping(target = "courses", expression = "java(mapCourses(student))")
    StudentDto toDto(Student student);
    Student toEntity(StudentDto dto);

    default List<CourseDto> mapCourses(Student student) {
        if (student.getEnrollments() == null) return List.of();
        return student.getEnrollments()
                .stream()
                .map(enrollment -> {
                    Course course = enrollment.getCourse();
                    return CourseDto.builder()
                            .courseId(course.getCourseId())
                            .courseNameEn(course.getCourseNameEn())
                            .courseNameAr(course.getCourseNameAr())
                            .courseCode(course.getCourseCode())
                            .description(course.getDescription())
                            .credits(course.getCredits())
                            .build();
                })
                .toList();
    }
}