package com.example.microserviceAuthentification.security.controllers;

import com.example.microserviceAuthentification.security.entities.Role;
import com.example.microserviceAuthentification.security.entitiesDto.UserDto;
import com.example.microserviceAuthentification.security.services.impl.AccountService;
import com.example.microserviceAuthentification.security.services.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
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
    @PostMapping("/add-role-to-user/{roleId}/{userId}")
    public ResponseEntity<Void> addRoleToUser(@PathVariable Long roleId,@PathVariable Long userId){
        accountService.addRoleToUser(roleId,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
