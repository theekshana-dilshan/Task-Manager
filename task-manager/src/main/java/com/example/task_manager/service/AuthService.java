package com.example.task_manager.service;


import com.example.greenshadowbackendspringboot.dto.impl.UserDTO;
import com.example.greenshadowbackendspringboot.secure.JWTAuthResponse;
import com.example.greenshadowbackendspringboot.secure.SignIn;

public interface AuthService {
   JWTAuthResponse signIn(SignIn signIn);
   JWTAuthResponse signUp(UserDTO userDTO);
   JWTAuthResponse refreshToken(String accessToken);
}
