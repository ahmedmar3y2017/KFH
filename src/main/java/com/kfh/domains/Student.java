package com.kfh.domains;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false)
    private String password; // BCrypt encoded

    @Column(nullable = false, length = 50)
    private String role = "ROLE_STUDENT";

    @Builder.Default
    private Boolean enabled = true;

    @Column(nullable = false, length = 200)
    private String fullNameEn;

    @Column(nullable = false, length = 200)
    private String fullNameAr;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    private String telephone;
    private String address;

    private LocalDateTime lastLoginAt;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentCourse> enrollments = new HashSet<>();
}
