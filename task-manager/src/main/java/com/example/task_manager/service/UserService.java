package com.example.task_manager.service;

import com.example.task_manager.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUser(String email);
    void deleteUser(String email);
    void updateUser(String email, UserDTO userDTO);
    UserDetailsService userDetailsService();
}
