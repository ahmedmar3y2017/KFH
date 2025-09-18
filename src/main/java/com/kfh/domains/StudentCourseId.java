package com.kfh.domains;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class StudentCourseId implements Serializable {
    private Long studentId;
    private Long courseId;
}
