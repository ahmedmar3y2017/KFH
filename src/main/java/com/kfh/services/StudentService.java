package com.kfh.services;

import com.kfh.domains.Student;
import com.kfh.dtos.StudentDto;
import com.kfh.mappers.StudentMapper;
import com.kfh.repos.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .toList();
    }

    public StudentDto save(Student student) {
        Student saved = studentRepository.save(student);
        return studentMapper.toDto(saved);
    }

    public void deleteStudent(long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
        }
    }
}