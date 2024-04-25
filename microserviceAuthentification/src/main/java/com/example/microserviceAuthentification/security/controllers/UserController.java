package com.example.microserviceAuthentification.security.controllers;

import com.example.microserviceAuthentification.security.entitiesDto.UserDto;
import com.example.microserviceAuthentification.security.services.IUserService;
import com.example.microserviceAuthentification.security.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService accountService;
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userDtos = accountService.getAllUser();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
    @GetMapping("/by-user-name/{userName}")
    public ResponseEntity<UserDto> getUserByUserName(@PathVariable String userName){
        UserDto userDto = accountService.getByUserName(userName);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @GetMapping("/by-id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto userDto = accountService.getById(id);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @PostMapping("/save-user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createUserDto = accountService.addNewUser(userDto);
        return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
    }
    @PostMapping("/add-role-to-user/{userId}/{roleId}")
    public ResponseEntity<Void> addRoleToUser(@PathVariable Long userId,@PathVariable Long roleId){
        accountService.addRoleToUser(userId,roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/remove-role-to-user/{userId}/{roleId}")
    public ResponseEntity<Void> removeRoleToUser(@PathVariable Long userId,@PathVariable Long roleId){
        accountService.removeRoleToUser(userId,roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
