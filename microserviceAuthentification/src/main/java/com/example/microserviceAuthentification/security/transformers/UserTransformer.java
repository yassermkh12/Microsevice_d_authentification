package com.example.microserviceAuthentification.security.transformers;

import com.example.microserviceAuthentification.security.entities.User;
import com.example.microserviceAuthentification.security.entitiesDto.UserDto;

public class UserTransformer {
    public static UserDto entityToDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

    public static User dtoToEntity(UserDto userDto){
        User user = new User();

        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        return user;
    }
}
