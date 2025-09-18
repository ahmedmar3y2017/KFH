package com.kfh.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCourseDto {
    private Long studentId;
    private Long courseId;
    private LocalDateTime enrolledAt;
}
