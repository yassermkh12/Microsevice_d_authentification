package com.example.microserviceAuthentification.security.controllers;

import com.example.microserviceAuthentification.security.entitiesDto.RoleDto;
import com.example.microserviceAuthentification.security.entitiesDto.UserDto;
import com.example.microserviceAuthentification.security.services.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userDtos = accountService.getAllUser();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<RoleDto>> getAllRole(){
        List<RoleDto> roleDtos = accountService.getAllRole();
        return new ResponseEntity<>(roleDtos, HttpStatus.OK);
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
    @PostMapping("/save-role")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto){
        RoleDto createRoleDto = accountService.addNewRole(roleDto);
        return new ResponseEntity<>(createRoleDto,HttpStatus.CREATED);
    }
    @PostMapping("{role}/to/{user}")
    public ResponseEntity<Void> addRoleToUser(@PathVariable String userName,@PathVariable String roleName){
        accountService.addRoleToUser(userName,roleName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
