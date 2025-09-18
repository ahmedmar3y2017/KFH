package com.kfh.dtos;

import lombok.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private Long studentId;
    private String username;
    private String fullNameEn;
    private String fullNameAr;
    private String email;
    private String telephone;
    private String address;
    private Set<StudentCourseDto> enrollments;
}