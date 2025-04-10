package com.example.task_manager.service.impl;


import com.example.task_manager.dao.UserDao;
import com.example.task_manager.dto.impl.UserDTO;
import com.example.task_manager.entity.impl.UserEntity;
import com.example.task_manager.exception.DataPersistException;
import com.example.task_manager.exception.UserNotFoundException;
import com.example.task_manager.service.UserService;
import com.example.task_manager.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity savedUser =
                userDao.save(mapping.toUserEntity(userDTO));
        if (savedUser == null) {
            throw new DataPersistException("User not saved");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> allUsers = userDao.findAll();
        return mapping.asUserDTOList(allUsers);
    }

    @Override
    public UserDTO getUser(String id) {
        if (userDao.existsById(id)){
            return mapping.toUserDTO(userDao.getReferenceById(id));
        }
        return null;
    }

    @Override
    public void deleteUser(String email) {
        Optional<UserEntity> existsUser = userDao.findByEmail(email);
        if (!existsUser.isPresent()){
            throw new UserNotFoundException("User with id " + email + " not found");
        }else {
            userDao.deleteByEmail(email);
        }
    }

    @Override
    public void updateUser(String email, UserDTO userDTO) {
        Optional<UserEntity> tempUser = userDao.findByEmail(email);
        if (tempUser.isPresent()){
            tempUser.get().setEmail(userDTO.getEmail());
            tempUser.get().setPassword(userDTO.getPassword());
        }
    }

    @Override
    public UserDetailsService userDetailsService() {
        return    userName-> userDao.findByEmail(userName).orElseThrow(()-> new UserNotFoundException("User not found"));
    }
}
