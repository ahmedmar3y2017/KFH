package com.kfh.repos;

import com.kfh.domains.StudentCourse;
import com.kfh.domains.StudentCourseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {
}
