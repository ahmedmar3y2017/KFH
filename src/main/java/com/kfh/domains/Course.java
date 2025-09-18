package com.kfh.domains;



import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false, length = 200)
    private String courseNameEn;

    @Column(nullable = false, length = 200)
    private String courseNameAr;

    @Column(nullable = false, unique = true, length = 50)
    private String courseCode;

    private String description;
    private Integer credits;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentCourse> enrollments = new HashSet<>();
}