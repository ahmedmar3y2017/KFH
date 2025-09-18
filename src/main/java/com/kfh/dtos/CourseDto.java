package com.kfh.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    private Long courseId;
    private String courseNameEn;
    private String courseNameAr;
    private String courseCode;
    private String description;
    private Integer credits;
}
