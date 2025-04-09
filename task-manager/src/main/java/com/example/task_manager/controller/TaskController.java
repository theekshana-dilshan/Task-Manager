package com.example.task_manager.controller;

import com.example.task_manager.customStatusCodes.SelectedErrorStatus;
import com.example.task_manager.dto.CustomStatus;
import com.example.task_manager.dto.impl.TaskDTO;
import com.example.task_manager.exception.DataPersistException;
import com.example.task_manager.exception.TaskNotFoundException;
import com.example.task_manager.service.TaskService;
import com.example.task_manager.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/task")
@CrossOrigin
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveTask(@RequestBody TaskDTO taskDTO) {
        try {
            taskService.saveTask(taskDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{taskId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomStatus getSelectedTask(@PathVariable("taskId") String taskId){
        if (!RegexProcess.taskIdMatcher(taskId)) {
            return new SelectedErrorStatus(1,"Task ID is not valid"+taskId);
        }
        return taskService.getTask(taskId);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDTO> getALlTasks(){
        return taskService.getAllTasks();
    }


    @DeleteMapping(value = "/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable ("taskId") String taskId){
        try {
            if (!RegexProcess.taskIdMatcher(taskId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            taskService.deleteTask(taskId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (TaskNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = "/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable ("taskId") String taskId,
                                           @RequestBody TaskDTO updatedTaskDTO){
        //validations
        try {
            if(!RegexProcess.taskIdMatcher(taskId) || updatedTaskDTO == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            taskService.updateTask(taskId,updatedTaskDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (TaskNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
