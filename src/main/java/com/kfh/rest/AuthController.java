package com.kfh.rest;


import com.kfh.domains.Student;
import com.kfh.dtos.AuthRequest;
import com.kfh.dtos.AuthResponse;
import com.kfh.dtos.SignupRequest;
import com.kfh.repos.StudentRepository;
import com.kfh.security.StudentDetails;
import com.kfh.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        StudentDetails userDetails = (StudentDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest req) {
        if (studentRepository.existsByUsername(req.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
        if (studentRepository.existsByEmail(req.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
        }

        Student student = Student.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .role("ROLE_STUDENT")
                .fullNameEn(req.getFullNameEn())
                .fullNameAr(req.getFullNameAr())
                .email(req.getEmail())
                .telephone(req.getTelephone())
                .address(req.getAddress())
                .build();

        Student saved = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created: " + saved.getStudentId());
    }
}