package com.example.microserviceAuthentification.security.services.impl;

import com.example.microserviceAuthentification.security.entities.Role;
import com.example.microserviceAuthentification.security.entities.User;
import com.example.microserviceAuthentification.security.entitiesDto.RoleDto;
import com.example.microserviceAuthentification.security.entitiesDto.UserDto;
import com.example.microserviceAuthentification.security.repositories.IRoleRepository;
import com.example.microserviceAuthentification.security.repositories.IUserRepository;
import com.example.microserviceAuthentification.security.transformers.RoleTransformer;
import com.example.microserviceAuthentification.security.transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;

    public List<UserDto> getAllUser(){
        List<User> users = userRepository.findAll();
        return UserTransformer.entityToDtoList(users);
    }

    public List<RoleDto> getAllRole(){
        List<Role> roles = roleRepository.findAll();
        return RoleTransformer.entityToDtoList(roles);
    }

    public UserDto addNewUser(UserDto userDto){
        User createUser = UserTransformer.dtoToEntity(userDto);
        userRepository.save(createUser);
        return UserTransformer.entityToDto(createUser);
    }

    public RoleDto addNewRole(RoleDto roleDto){
        Role createRole = RoleTransformer.dtoToEntity(roleDto);
        roleRepository.save(createRole);
        return RoleTransformer.entityToDto(createRole);
    }

    public void addRoleToUser(String userName, String roleName){
        User user = userRepository.findByUserName(userName);
        Role role = roleRepository.findByRoleName(roleName);

        user.getRoles().add(role);
    }

    public UserDto getById(Long id){
        User userById = userRepository.findById(id).orElse(null);
        return UserTransformer.entityToDto(userById);
    }

    public UserDto getByUserName(String userName){
        User user = userRepository.findByUserName(userName);
        return UserTransformer.entityToDto(user);
    }
}
