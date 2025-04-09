package com.example.task_manager.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskEntity {
    @Id
    private String id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
}
