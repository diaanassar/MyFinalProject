package com.example.myfinalproject.Service;


import com.example.myfinalproject.Entities.Student;
import com.example.myfinalproject.Repository.StudentRepository;
import com.example.myfinalproject.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.myfinalproject.dto.LogInResponseDTO;
import com.example.myfinalproject.dto.LoginRequestDTO;


@Service
@Slf4j
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final StudentRepository studentRepository;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
                           JwtUtil jwtUtil, StudentRepository studentRepository){//, BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.studentRepository = studentRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LogInResponseDTO login(LoginRequestDTO loginRequest) throws Exception {

        // data type from security dependency
        Authentication result = null;

        try {
            // Authentication manager is an interface that comes with spring security authentication package
//            String encodedPass = passwordEncoder.encode(loginRequest.getPassword());
//            System.out.println("encodedPass = "+encodedPass);
            result = authenticationManager.authenticate(
                    // also comes with spring security
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            System.out.println("----------------------------");
            throw new BadCredentialsException(e.getMessage());
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());
//        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        final String accessToken = jwtUtil.generateToken(userDetails);
        Student users = studentRepository.getStudentByEmail(loginRequest.getEmail());
        return new LogInResponseDTO( users.getId(), users.getEmail(), accessToken);
    }

}
