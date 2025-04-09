package com.example.task_manager.service.impl;

import com.example.task_manager.customStatusCodes.SelectedErrorStatus;
import com.example.task_manager.dao.TaskDao;
import com.example.task_manager.dto.CustomStatus;
import com.example.task_manager.dto.impl.TaskDTO;
import com.example.task_manager.entity.impl.TaskEntity;
import com.example.task_manager.exception.DataPersistException;
import com.example.task_manager.exception.TaskNotFoundException;
import com.example.task_manager.service.TaskService;
import com.example.task_manager.util.Mapping;
import com.example.task_manager.util.AppUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskServiceIMPL implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private Mapping taskMapping;

    @Override
    public void saveTask(TaskDTO taskDTO) {
        /*taskDTO.setId(Long.valueOf(AppUtil.generateTaskId()));*/
        TaskEntity savedTask =
                taskDao.save(taskMapping.toTaskEntity(taskDTO));
        if(savedTask == null){
            throw new DataPersistException("Task not saved");
        }
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskMapping.asTaskDTOList( taskDao.findAll());
    }

    @Override
    public CustomStatus getTask(String taskId) {
        if(taskDao.existsById(taskId)){
            var selectedUser = taskDao.getReferenceById(taskId);
            return taskMapping.toTaskDTO(selectedUser);
        }else {
            return new SelectedErrorStatus(2,"Selected task not found "+taskId);
        }
    }

    @Override
    public void deleteTask(String taskId) {
        Optional<TaskEntity> foundTask = taskDao.findById(taskId);
        if (!foundTask.isPresent()) {
            throw new TaskNotFoundException("Task not found");
        }else {
            taskDao.deleteById(taskId);
        }
    }

    @Override
    public void updateTask(String taskId, TaskDTO taskDTO) {
        Optional<TaskEntity> byId = taskDao.findById(taskId);

        if (byId.isPresent()) {
            TaskEntity taskEntity = byId.get();

            taskEntity.setTitle(taskDTO.getTitle());
            taskEntity.setDescription(taskDTO.getDescription());
            taskEntity.setStatus(taskDTO.getStatus());
            taskEntity.setCreatedAt(taskDTO.getCreatedAt());

            taskDao.save(taskEntity);
        } else {
            throw new EntityNotFoundException("Task entity with ID " + taskId + " not found.");
        }
    }
}
