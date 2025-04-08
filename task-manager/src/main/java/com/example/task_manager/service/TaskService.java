package com.example.task_manager.service;

import com.example.task_manager.dto.CustomStatus;
import com.example.task_manager.dto.impl.TaskDTO;

import java.util.List;

public interface TaskService {
    void saveTask(TaskDTO taskDTO);
    List<TaskDTO> getAllTasks();
    CustomStatus getTask(String taskId);
    void deleteTask(String taskId);
    void updateTask(String taskId, TaskDTO taskDTO);
}
