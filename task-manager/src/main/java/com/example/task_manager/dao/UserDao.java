package com.example.task_manager.dao;

import com.example.greenshadowbackendspringboot.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<UserEntity> deleteByEmail(String email);
    UserEntity findByStatusTrue();

}
