package com.example.task_manager.dto.impl;

import com.example.task_manager.dto.CustomStatus;
import com.example.task_manager.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO implements CustomStatus {
    private String id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
}
