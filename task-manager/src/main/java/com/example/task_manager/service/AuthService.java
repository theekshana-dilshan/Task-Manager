package com.example.task_manager.service;


import com.example.task_manager.dto.impl.UserDTO;
import com.example.task_manager.secure.JWTAuthResponse;
import com.example.task_manager.secure.SignIn;

public interface AuthService {
   JWTAuthResponse signIn(SignIn signIn);
   JWTAuthResponse signUp(UserDTO userDTO);
   JWTAuthResponse refreshToken(String accessToken);
}
