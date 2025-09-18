package com.kfh.mappers;


import com.kfh.domains.StudentCourse;
import com.kfh.dtos.StudentCourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentCourseMapper {

    @Mapping(target = "studentId", source = "student.studentId")
    @Mapping(target = "courseId", source = "course.courseId")
    StudentCourseDto toDto(StudentCourse enrollment);

    @Mapping(target = "student", ignore = true) // set manually in service
    @Mapping(target = "course", ignore = true)  // set manually in service
    StudentCourse toEntity(StudentCourseDto dto);
}