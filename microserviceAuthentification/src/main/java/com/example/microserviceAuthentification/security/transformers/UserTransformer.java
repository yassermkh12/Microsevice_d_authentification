package com.example.microserviceAuthentification.security.transformers;

import com.example.microserviceAuthentification.security.entities.User;
import com.example.microserviceAuthentification.security.entitiesDto.UserDto;

import java.util.*;
import java.util.stream.Collectors;

public class UserTransformer {
    public static UserDto entityToDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());

        return userDto;
    }

    public static User dtoToEntity(UserDto userDto){
        User user = new User();

        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        return user;
    }

    public static List<UserDto> entityToDtoList(List<User> users){
        return users.stream()
                .map(UserTransformer::entityToDto)
                .collect(Collectors.toList());
    }

    public static List<User> dtoToEntityList(List<UserDto> userDtos){
        return userDtos.stream()
                .map(UserTransformer::dtoToEntity)
                .collect(Collectors.toList());
    }

}
