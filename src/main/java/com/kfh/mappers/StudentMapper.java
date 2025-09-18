package com.kfh.mappers;


import com.kfh.domains.Student;
import com.kfh.dtos.StudentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StudentCourseMapper.class})
public interface StudentMapper {
    StudentDto toDto(Student student);
    Student toEntity(StudentDto dto);
}