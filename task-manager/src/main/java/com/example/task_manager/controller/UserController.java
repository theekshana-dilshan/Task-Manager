package com.example.task_manager.controller;

import com.example.greenshadowbackendspringboot.dto.impl.UserDTO;
import com.example.greenshadowbackendspringboot.exception.UserNotFoundException;
import com.example.greenshadowbackendspringboot.service.UserService;
import com.example.greenshadowbackendspringboot.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getSelectedUser(@PathVariable("email") String email){
        return userService.getUser(email);
    }


    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable("email") String email){
        try {
            if(!RegexProcess.userEmailMatcher(email)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            userService.deleteUser(email);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (UserNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINISTRATIVE')")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(
            @RequestPart ("email") String email,
            @RequestPart ("password") String password
    ){
        UserDTO buildUserDTO = new UserDTO();
        buildUserDTO.setEmail(email);
        buildUserDTO.setPassword(password);
        userService.updateUser(email,buildUserDTO);
    }
}
