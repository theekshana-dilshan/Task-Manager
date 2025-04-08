package com.example.task_manager.dao;

import com.example.task_manager.entity.impl.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends JpaRepository<TaskEntity, String> {

}
