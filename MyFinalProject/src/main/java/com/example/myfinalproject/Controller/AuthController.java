package com.example.myfinalproject.Controller;

import com.example.myfinalproject.Service.AuthService;
import com.example.myfinalproject.dto.LogInResponseDTO;
import com.example.myfinalproject.dto.LoginRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authenticate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) throws Exception {
        var loginResponse = authService.login(loginRequest);
        return new ResponseEntity<LogInResponseDTO>(
                loginResponse, HttpStatus.OK);
    }
}
