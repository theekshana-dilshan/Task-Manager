package com.example.task_manager.dto.impl;

import com.example.greenshadowbackendspringboot.dto.SuperDTO;
import com.example.greenshadowbackendspringboot.util.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements SuperDTO {
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    Role userRole;
    boolean status;
}
