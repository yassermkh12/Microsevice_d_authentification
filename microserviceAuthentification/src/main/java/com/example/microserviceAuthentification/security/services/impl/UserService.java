package com.example.microserviceAuthentification.security.services.impl;

import com.example.microserviceAuthentification.security.entities.Role;
import com.example.microserviceAuthentification.security.entities.User;
import com.example.microserviceAuthentification.security.entitiesDto.UserDto;
import com.example.microserviceAuthentification.security.exceptions.GlobalException;
import com.example.microserviceAuthentification.security.repositories.IRoleRepository;
import com.example.microserviceAuthentification.security.repositories.IUserRepository;
import com.example.microserviceAuthentification.security.services.IUserService;
import com.example.microserviceAuthentification.security.transformers.UserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUser(){
        List<User> users = userRepository.findAll();
        return UserTransformer.entityToDtoList(users);
    }

//    public List<RoleDto> getAllRole(){
//        List<Role> roles = roleRepository.findAll();
//        return RoleTransformer.entityToDtoList(roles);
//    }

    public UserDto addNewUser(UserDto userDto){
        User createUser = UserTransformer.dtoToEntity(userDto);
        String password = createUser.getPassword();
        createUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(createUser);
        return UserTransformer.entityToDto(createUser);
    }

//    public RoleDto addNewRole(RoleDto roleDto){
//        Role createRole = RoleTransformer.dtoToEntity(roleDto);
//        roleRepository.save(createRole);
//        return RoleTransformer.entityToDto(createRole);
//    }
//
//    public void addRoleToUser(String userName, String roleName){
//        User user = userRepository.findByUserName(userName);
//        Role role = roleRepository.findByRoleName(roleName);
//
//        user.getRoles().add(role);
//    }

    public UserDto getById(Long id){
        User userById = userRepository.findById(id).orElse(null);
        log.info("find by id user "+ userById);
        return UserTransformer.entityToDto(userById);
    }

    public UserDto getByUserName(String userName){
        User user = userRepository.findByUserName(userName);
        return UserTransformer.entityToDto(user);
    }

    public void addRoleToUser(Long userId,Long roleId){
        log.info("add role to user");
        User user = userRepository.findById(userId).orElse(null);
        log.info("user find by id : "+ user);
        Role role = roleRepository.findById(roleId).orElse(null);
        log.info("role find by id : "+ role);

        user.getRoles().add(role);
//        role.getUsers().add(user);
//        Set<Role> roles = null;
//        roles = user.getRoles();
//        roles.add(role);
//        user.setRoles(roles);

        userRepository.save(user);
        log.info("user finale : "+ user);
    }

    public void removeRoleToUser(Long userId, Long roleId){
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);

        user.getRoles().remove(role);

        userRepository.save(user);
    }

    public UserDto getByEmail(String email) throws GlobalException{
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new GlobalException("il n y a pas de user avec cet email")
        );
        if(user == null){
            throw new GlobalException("il n y a pas de user avec cet email");
        }
        return UserTransformer.entityToDto(user);
    }

    public void updatePasswordByEmail(String email, String password) throws GlobalException{
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new GlobalException("il n y a pas de user avec cet email")
        );

        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
    }
}
