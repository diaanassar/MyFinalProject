package com.example.myfinalproject.Service;


import com.example.myfinalproject.dto.LogInResponseDTO;
import com.example.myfinalproject.dto.LoginRequestDTO;

public interface AuthService {

    LogInResponseDTO login(LoginRequestDTO loginRequest) throws Exception;
//    LogInResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequest);
}
