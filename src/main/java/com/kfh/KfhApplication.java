package com.kfh;

import com.kfh.domains.Student;
import com.kfh.dtos.StudentDto;
import com.kfh.repos.StudentRepository;
import com.kfh.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KfhApplication implements CommandLineRunner {

    @Autowired
    StudentService studentService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(KfhApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // loop over students and update dummy hashed password -> 123

        for (Student student : studentRepository.findAll()) {
            String rawPassword = "123";

            if (student.getPassword() == null || !student.getPassword().startsWith("$2")) {
                student.setPassword(passwordEncoder.encode(rawPassword));
                StudentDto updated = studentService.save(student);

                System.out.printf("Updated password for student: %s (id=%d)%n",
                        updated.getUsername(), updated.getStudentId());
            }
        }
    }
}
