package com.kfh.mappers;


import com.kfh.domains.Course;
import com.kfh.dtos.CourseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDto toDto(Course course);
    Course toEntity(CourseDto dto);
}